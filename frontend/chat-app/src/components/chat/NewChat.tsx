import { useMemo } from "react";
import { useNavigate, useParams } from "react-router";
import useSWR, { mutate } from "swr";
import { fetchUsers } from "../../api/usersClient";
import { MessageContainer } from "./MessageContainer";
import type { Conversation, CreateMessageRequest } from "../../api/types";
import { useSelector } from "react-redux";
import { selectAuthUser } from "../../store/users/authUserSelectors";
import { sendMessageRequest } from "../../api/messageClient";

export const NewChat = () => {
  const { id } = useParams();
  const userId = Number(id);
  const authUser = useSelector(selectAuthUser);
  const navigate = useNavigate();
  const { data: users } = useSWR("/api/users", fetchUsers);
  const person = useMemo(
    () => users?.find((p) => p.id === userId),
    [users, id]
  );

  const sendMessage = async (message: string) => {
    console.log(message);
    if (authUser && person) {
      const participantIds = [userId, authUser.id];
      const request: CreateMessageRequest = {
        participantIds,
        senderId: authUser.id,
        text: message,
      };
      const returnedMessage = await sendMessageRequest(request);
      const { conversationId } = returnedMessage;
      const localConv: Conversation = {
        conversationId,
        conversationName: `${person?.firstName} ${person?.lastName}`,
        latestMessage: returnedMessage,
        participants: [person, authUser],
      };
      mutate(`/conversations?participant=${authUser.id}`, (convos = []) => [
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
