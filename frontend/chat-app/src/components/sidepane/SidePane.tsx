import { MagnifyingGlassIcon } from "@heroicons/react/16/solid";
import { UserList } from "./UserList";
import { useMemo, useState } from "react";
import { ConversationList } from "./ConversationList";
import { useSelector } from "react-redux";
import { selectAuthUserId } from "../../store/users/authUserSelectors";
import { fetchConversations } from "../../api/conversationClient";
import type { Conversation } from "../../api/types";
import { useSWRWithAuth } from "../../errorhandling/useSWRWithAuth";

export const SidePane = () => {
  const authUserId = useSelector(selectAuthUserId);
  const { data, error, isLoading } = useSWRWithAuth(
    `/conversations?participant=${authUserId}`,
    fetchConversations
  );

  if (isLoading) return "Loading convos...";
  if (error) return error.toString();
  if (!data) return null;

  return <SidePaneMain authUserId={authUserId} convos={data} />;
};

interface SidePaneMainProps {
  authUserId: number;
  convos: Conversation[];
}
const SidePaneMain = ({ authUserId, convos }: SidePaneMainProps) => {
  const [searchText, setSearchText] = useState("");
  const directMessages = useMemo(() => {
    const ids = new Set<number>();
    for (let { participants } of convos) {
      if (participants.length == 1) {
        ids.add(authUserId);
      } else if (participants.length == 2) {
        const directPersonId =
          participants[0].id == authUserId
            ? participants[1].id
            : participants[0].id;
        ids.add(directPersonId);
      }
    }
    return ids;
  }, [authUserId, convos]);

  return (
    <div className="h-full flex flex-col">
      <label className="input">
        <MagnifyingGlassIcon className="size-6" />
        <input
          type="search"
          className="grow"
          placeholder="Search"
          value={searchText}
          onChange={(e) => setSearchText(e.target.value)}
        />
      </label>
      <div className="flex-1 overflow-auto shadow-md">
        {<ConversationList conversations={convos} />}
        <UserList searchText={searchText} excludeUserIds={directMessages} />
      </div>
    </div>
  );
};
