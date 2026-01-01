import {
  NavigableList,
  type NavigableListElement,
} from "../../shared/NavigableList";
import type { ConversationSummary } from "../../api/types";

interface ConversationListProps {
  conversations: ConversationSummary[];
}
export const ConversationList = ({ conversations }: ConversationListProps) => {
  const listItems: NavigableListElement[] = conversations.map(
    ({ name, conversationId, messageCount }) => ({
      id: conversationId,
      title: name,
      subTitle: messageCount.toString(),
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
