import { useSelector } from "react-redux";
import { useParams } from "react-router";
import { selectAuthUserId } from "../../store/users/authUserSelectors";
import { MessageContainer } from "./MessageContainer";
import useSWR, { mutate } from "swr";
import { ChatBubble } from "./ChatBubble";
import type { CreateMessageRequest } from "../../api/types";
import { fetchMessages, sendMessageRequest } from "../../api/messageClient";
import { fetchConversation } from "../../api/conversationClient";
import { ApiErrorView } from "../../errorhandling/ApiErrorView";

export const ConversationChat = () => {
  const authUserId = useSelector(selectAuthUserId);
  if (!authUserId) {
    return <div>Log in to continue</div>;
  }
  return <ConversationChatAuthenticated authUserId={authUserId} />;
};

const ConversationChatAuthenticated = ({
  authUserId,
}: {
  authUserId: number;
}) => {
  const { id } = useParams();
  const conversationId = Number(id);
  const { data: messages, error } = useSWR(
    `/messages/${conversationId}`,
    fetchMessages,
  );
  const { data: conversation } = useSWR(
    `/conversations/${conversationId}?authUserId=${authUserId}`,
    fetchConversation,
  );

  if (!conversation) {
    return <div>{`No conversation found`}</div>;
  }

  if (error) {
    return <ApiErrorView title="Messages" error={error} />;
  }

  if (!messages) {
    return <div>{`Loading conversation ${conversationId}...`}</div>;
  }

  const sendMessage = async (message: string) => {
    const request: CreateMessageRequest = {
      conversationId,
      senderId: authUserId,
      text: message,
    };
    const response = await sendMessageRequest(request);
    mutate(`/messages/${conversationId}`, [...messages, response]);
  };

  return (
    <MessageContainer sendMessage={sendMessage}>
      <div className="flex flex-col flex-1">
        {messages.map((message, index) => (
          <>
            {index == 0 ||
              (message.creationDate.toDateString() !==
                messages[index - 1].creationDate.toDateString() && (
                <div className="badge m-2 self-center">
                  {message.creationDate.toDateString()}
                </div>
              ))}
            <ChatBubble
              key={message.messageId}
              message={message}
              participants={conversation.participants}
              authUserId={authUserId}
            />
          </>
        ))}
      </div>
    </MessageContainer>
  );
};
