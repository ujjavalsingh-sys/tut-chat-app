import useSWR from "swr";
import { fetchUsers } from "../api/api";
import {
  NavigableList,
  type NavigableListElement,
} from "../shared/NavigableList";
import { useSelector } from "react-redux";
import { selectAuthUserId } from "../store/users/userSelectors";

export const UserList = () => {
  const { data, error } = useSWR("/api/users", fetchUsers);
  if (error) return <div> {error.toString()}</div>;
  if (!data) return "Loading";

  const authUserId = useSelector(selectAuthUserId);
  const listItems: NavigableListElement[] = data.map(
    ({ id, username, firstName, lastName }) => ({
      id,
      title: `${firstName} ${lastName}${id == authUserId ? " (You)" : ""}`,
      subTitle: username,
    })
  );

  return (
    <NavigableList
      title="Users"
      urlPrefix="/dashboard/person"
      items={listItems}
    />
  );
};
