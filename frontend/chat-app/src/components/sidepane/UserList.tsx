import useSWR from "swr";
import { fetchUsers } from "../../api/usersClient";
import {
  NavigableList,
  type NavigableListElement,
} from "../../shared/NavigableList";
import { useSelector } from "react-redux";
import { selectAuthUserId } from "../../store/users/authUserSelectors";

interface UserListProps {
  searchText: string;
  excludeUserIds: Set<number>;
}

export const UserList = ({ searchText, excludeUserIds }: UserListProps) => {
  const { data, error } = useSWR("/api/users", fetchUsers);
  const authUserId = useSelector(selectAuthUserId);

  if (error) return <div> {error.toString()}</div>;
  if (!data) return "Loading";

  const listItems: NavigableListElement[] = data
    .filter(({ id }) => !excludeUserIds.has(id))
    .filter(
      ({ username, firstName, lastName }) =>
        !searchText ||
        username.includes(searchText) ||
        firstName.includes(searchText) ||
        lastName.includes(searchText)
    )
    .map(({ id, username, firstName, lastName }) => ({
      id,
      title: `${firstName} ${lastName}${id == authUserId ? " [You]" : ""}`,
      subTitle: username,
    }));

  return (
    <NavigableList
      title="Users"
      urlPrefix="/dashboard/person"
      items={listItems}
    />
  );
};
