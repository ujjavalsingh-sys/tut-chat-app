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
        
        <li className="p-4 pb-2 text-xs opacity-60 tracking-wide">Users</li>
        
        <li className="list-row rounded-none border-b border-gray-200">
            <div><img className="size-10 rounded-box" src="https://img.daisyui.com/images/profile/demo/1@94.webp"/></div>
            <div>
                <div>Dio Lupa</div>
                <div className="text-xs uppercase font-semibold opacity-60">Remaining Reason</div>
            </div>
        </li>
        
        <li className="list-row">
            <div><img className="size-10 rounded-box" src="https://img.daisyui.com/images/profile/demo/1@94.webp"/></div>
            <div>
            <div>Dio Lupa</div>
            <div className="text-xs uppercase font-semibold opacity-60">Remaining Reason</div>
            </div>
        </li>
        
       
        </ul>
    )
}