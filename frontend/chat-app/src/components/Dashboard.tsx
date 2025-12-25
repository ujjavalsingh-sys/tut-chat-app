import { Outlet } from "react-router"

export const Dashboard = () => {
    return (
        <div>
            <h2>Navbar</h2>
            <Outlet />
            <h2>Footer</h2>
        </div>
    )
}