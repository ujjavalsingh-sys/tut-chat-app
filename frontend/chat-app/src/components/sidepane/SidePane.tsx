import { MagnifyingGlassIcon } from "@heroicons/react/16/solid";
import { UserList } from "./UserList";
import { useMemo, useState } from "react";
import { ConversationList } from "./ConversationList";
import { useSelector } from "react-redux";
import { selectAuthUserId } from "../../store/users/authUserSelectors";
import useSWR from "swr";
import { fetchConversations } from "../../api/conversationClient";
import type { Conversation } from "../../api/types";

export const SidePane = () => {
  const authUserId = useSelector(selectAuthUserId);
  const { data: convos, error } = useSWR(
    `/conversations?participant=${authUserId}`,
    fetchConversations
  );

  if (!authUserId) return "Log in to load conversations";
  if (error) return <div> {error.toString()}</div>;
  if (!convos) return "Loading convos...";
  return <SidePaneMain authUserId={authUserId} convos={convos} />;
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
