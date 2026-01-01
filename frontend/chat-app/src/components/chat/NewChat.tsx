import { useMemo } from "react";
import { useNavigate, useParams } from "react-router";
import useSWR, { mutate } from "swr";
import { fetchUsers } from "../../api/api";
import { MessageContainer } from "./MessageContainer";
import type { ConversationSummary, MessageRequest } from "../../api/types";
import { useSelector } from "react-redux";
import { selectAuthUserId } from "../../store/users/authUserSelectors";
import { sendMessageRequest } from "../../api/messageClient";

export const NewChat = () => {
  const { id } = useParams();
  const userId = Number(id);
  const authUserId = useSelector(selectAuthUserId);
  const navigate = useNavigate();
  const { data: users } = useSWR("/api/users", fetchUsers);
  const person = useMemo(
    () => users?.find((p) => p.id === userId),
    [users, id]
  );

  const sendMessage = async (message: string) => {
    console.log(message);
    if (authUserId) {
      const participantIds = [{ personId: userId }, { personId: authUserId }];
      const request: MessageRequest = {
        participantIds,
        senderId: authUserId,
        text: message,
      };
      const returnedMessage = await sendMessageRequest(request);
      const { conversationId } = returnedMessage;
      const localConv: ConversationSummary = {
        name: `${person?.firstName} ${person?.lastName}`,
        conversationId,
        messageCount: 1,
        participants: participantIds,
      };
      mutate(`/conversations?participant=${authUserId}`, (convos = []) => [
        localConv,
        ...convos,
      ]);

      navigate(`/dashboard/conversation/${conversationId}`);
    }
  };

  if (!person) {
    return <div>Unknown person #{id}</div>;
  }
  return (
    <MessageContainer sendMessage={sendMessage}>
      <div className="absolute flex-1 h-full w-full z-1 flex flex-col justify-end items-center overflow-auto">
        <div className="badge m-2">
          No messages for {person.firstName} {person.lastName}
        </div>
      </div>
    </MessageContainer>
  );
};
