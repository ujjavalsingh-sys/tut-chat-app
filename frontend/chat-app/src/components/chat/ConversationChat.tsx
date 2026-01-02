import { useSelector } from "react-redux";
import { useParams } from "react-router";
import { selectAuthUserId } from "../../store/users/authUserSelectors";
import { MessageContainer } from "./MessageContainer";
import useSWR, { mutate } from "swr";
import { ChatBubble } from "./ChatBubble";
import type { CreateMessageRequest } from "../../api/types";
import { fetchMessages, sendMessageRequest } from "../../api/messageClient";
import { fetchConversations } from "../../api/conversationClient";
import { useMemo } from "react";

export const ConversationChat = () => {
  const { id } = useParams();
  const conversationId = Number(id);
  const authUserId = useSelector(selectAuthUserId);
  const { data: messages, error } = useSWR(
    `/messages/${conversationId}`,
    fetchMessages
  );
  const { data: convos } = useSWR(
    `/conversations?participant=${authUserId}`,
    fetchConversations
  );
  const conversation = useMemo(
    () => convos?.find(({ conversationId: cId }) => cId === conversationId),
    [convos, conversationId]
  );

  const sendMessage = async (message: string) => {
    if (authUserId && messages) {
      const request: CreateMessageRequest = {
        conversationId,
        senderId: authUserId,
        text: message,
      };
      const response = await sendMessageRequest(request);
      mutate(`/messages/${conversationId}`, [...messages, response]);
    }
  };

  return (
    <MessageContainer sendMessage={sendMessage}>
      {!authUserId ? (
        <div>Log in to continue</div>
      ) : !conversation ? (
        <div>{`No conversation found`}</div>
      ) : error ? (
        <div>{`Error loading conversation: ${error.message}`}</div>
      ) : messages ? (
        <div className="flex flex-col flex-1">
          {messages.map((message) => (
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
