import useSWR from "swr"
import { fetchUsers } from "../api/api"

export const UserList = () => {
    const { data, error } = useSWR("/api/users", fetchUsers);
    if(error) return error;
    if (!data) return "Loading";
    return (
        <div>
            User list
            {data.length}
        </div>
    )
}