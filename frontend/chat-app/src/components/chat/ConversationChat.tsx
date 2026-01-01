import { useSelector } from "react-redux";
import { useParams } from "react-router";
import { selectAuthUserId } from "../../store/users/authUserSelectors";
import { MessageContainer } from "./MessageContainer";
import useSWR, { mutate } from "swr";
import { fetchConversation } from "../../api/conversationClient";
import { ChatBubble } from "./ChatBubble";
import type { MessageRequest } from "../../api/types";
import { useErrorHandler } from "../../errorhandling/useErrorHandler";
import { sendMessageRequest } from "../../api/messageClient";

export const ConversationChat = () => {
  const { id } = useParams();
  const conversationId = Number(id);
  const authUserId = useSelector(selectAuthUserId);
  const { data: conversation, error } = useSWR(
    `/conversations/${conversationId}`,
    fetchConversation
  );

  const sendMessage = async (message: string) => {
    console.log(message);
    if (authUserId && conversation) {
      const request: MessageRequest = {
        conversationId,
        senderId: authUserId,
        text: message,
      };
      const response = await sendMessageRequest(request);
      mutate(`/conversations/${conversationId}`, {
        ...conversation,
        messages: [...conversation.messages, response],
      });
    }
  };

  return (
    <MessageContainer sendMessage={sendMessage}>
      {!authUserId ? (
        <div>Log in to continue</div>
      ) : error ? (
        <div>{`Error loading conversation: ${error.message}`}</div>
      ) : conversation ? (
        <div className="flex flex-col flex-1">
          {conversation.messages.map((message) => (
            <ChatBubble
              key={message.messageId}
              message={message}
              participants={conversation.participants}
              authUserId={authUserId}
            />
          ))}
        </div>
      ) : (
        <div>{`Loading conversation ${conversationId}...`}</div>
      )}
    </MessageContainer>
  );
};
