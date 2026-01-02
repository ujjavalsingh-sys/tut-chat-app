import type { Conversation } from "../../api/types";
import {
  NavigableList,
  type NavigableListElement,
} from "../../shared/NavigableList";

interface ConversationListProps {
  conversations: Conversation[];
}
export const ConversationList = ({ conversations }: ConversationListProps) => {
  const listItems: NavigableListElement[] = conversations.map(
    ({ conversationName, conversationId, latestMessage }) => ({
      id: conversationId,
      title: conversationName,
      subTitle: latestMessage.message,
    })
  );

  return (
    <NavigableList
      title="Conversations"
      urlPrefix="/dashboard/conversation"
      items={listItems}
    />
  );
};
