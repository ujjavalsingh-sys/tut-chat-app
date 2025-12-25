import useSWR from "swr"
import { fetchUsers } from "../api/api"

export const UserList = () => {
    const { data, error } = useSWR("/api/users", fetchUsers);
    if(error) return (
        <div> {error.toString()}</div>
    );
    if (!data) return "Loading";
    return (
        <ul className="list bg-base-100 shadow-md">
        
        <li className="p-4 pb-2 text-xs opacity-60 tracking-wide">Users: {data.length.toString()}</li>

        {
            data.length == 0
            ? <li className="list-row"><i>No user found</i></li>
            : data.map(({id, username }) => (
                <li key={id} className="list-row rounded-none border-b border-gray-200">
                    {/* <div><img className="size-10 rounded-box" src="https://img.daisyui.com/images/profile/demo/1@94.webp"/></div> */}
                    <div className="text-4xl font-thin opacity-30 tabular-nums">{id}</div>
                    <div>
                        <div>{username}</div>
                        <div className="text-xs uppercase font-semibold opacity-60">Remaining Reason</div>
                    </div>
                </li>
            ))
        }

        </ul>
    )
}