import { MagnifyingGlassIcon } from "@heroicons/react/16/solid";
import { UserList } from "./UserList";
import { useMemo, useState } from "react";
import { ConversationList } from "./ConversationList";
import { useSelector } from "react-redux";
import { selectAuthUserId } from "../../store/users/authUserSelectors";
import { fetchConversations } from "../../api/conversationClient";
import type { Conversation } from "../../api/types";
import { useSWRWithAuth } from "../../errorhandling/useSWRWithAuth";
import { ApiErrorView } from "../../errorhandling/ApiErrorView";
import { centeredFlex } from "../../shared/cssConstants";

export const SidePane = () => {
  const authUserId = useSelector(selectAuthUserId);
  const { data, error, isLoading } = useSWRWithAuth(
    `/conversations?participant=${authUserId}`,
    fetchConversations,
  );

  return (
    <div className={centeredFlex}>
      {isLoading ? (
        <>
          <label className="text-xs">Conversations</label>
          <div className="skeleton h-6 mx-5 my-2"></div>
        </>
      ) : error ? (
        <ApiErrorView title="Conversations" error={error} />
      ) : !data ? null : (
        <SidePaneMain authUserId={authUserId} convos={data} />
      )}
    </div>
  );
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
    <>
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
      <div className="flex-1 w-full text-start overflow-auto shadow-md">
        <ConversationList conversations={convos} />
        <UserList searchText={searchText} excludeUserIds={directMessages} />
      </div>
    </>
  );
};
