import { Outlet, useNavigate } from "react-router"
import { UserList } from "./UserList"
import { useState } from "react"

export const Dashboard = () => {
    const navigate = useNavigate();
    const [isSidePanelVisible, setIsSidePanelVisible] = useState(true);

    const handleToggleSidePanel = () => {
        setIsSidePanelVisible(!isSidePanelVisible);
    }

    const handleLogout = () => {
        navigate("/");
    }
    return (
        <div className="flex h-screen flex-col">
            <div className="navbar bg-base-100 shadow-sm">
                <div className="flex-none">
                    <button className="btn btn-square btn-ghost" onClick={handleToggleSidePanel}>
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" className="inline-block h-5 w-5 stroke-current"> <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16M4 18h16"></path> </svg>
                    </button>
                </div>
                <div className="flex-1">
                    <a className="btn btn-ghost text-xl">Chat On</a>
                </div>
                <div className="flex-none">
                    <button className="btn btn-square btn-ghost" onClick={handleLogout}>
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" className="inline-block h-5 w-5 stroke-current"> <path stroke-linecap="round" stroke-linejoin="round" strokeWidth="2" d="M15.75 9V5.25A2.25 2.25 0 0013.5 3h-6A2.25 2.25 0 005.25 5.25v13.5A2.25 2.25 0 007.5 21h6a2.25 2.25 0 002.25-2.25V15m3 0l3-3m0 0l-3-3m3 3H9" /> </svg>
                    </button>
                </div>
            </div>
            <div className="flex flex-row flex-1 overflow-auto">
                {
                    isSidePanelVisible && (
                    <div className="w-64 overflow-auto">
                        <UserList />
                    </div>
                    )
                }
                <div className="flex-1">
                    <Outlet />
                </div>
            </div>
            <div>Footer</div>
        </div>
    )
}