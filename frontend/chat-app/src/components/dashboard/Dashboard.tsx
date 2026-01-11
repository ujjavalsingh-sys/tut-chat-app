import { Outlet, useNavigate } from "react-router";
import { useState } from "react";
import { SidePane } from "../sidepane/SidePane";
import {
  ArrowLeftStartOnRectangleIcon,
  Bars3Icon,
} from "@heroicons/react/16/solid";
import { useSelector } from "react-redux";
import { selectAuthUserName } from "../../store/users/authUserSelectors";

export const Dashboard = () => {
  const navigate = useNavigate();
  const [isSidePanelVisible, setIsSidePanelVisible] = useState(true);

  const authUserName = useSelector(selectAuthUserName);

  const logout = () => {
    navigate("/");
  };

  const handleToggleSidePanel = () => {
    setIsSidePanelVisible(!isSidePanelVisible);
  };

  const handleLogout = () => {
    logout();
  };
  return (
    <div className="flex flex-1 w-full flex-col">
      <div className="navbar bg-base-100 shadow-sm">
        <div className="flex-none">
          <button
            className="btn btn-square btn-ghost"
            onClick={handleToggleSidePanel}
          >
            <Bars3Icon className="size-6" />
          </button>
        </div>
        <div className="flex-1">
          <a className="btn btn-ghost text-xl">
            Chat On <i>{authUserName}</i>
          </a>
        </div>
        <div className="flex-none">
          <button className="btn btn-square btn-ghost" onClick={handleLogout}>
            <ArrowLeftStartOnRectangleIcon className="size-6" />
          </button>
        </div>
      </div>
      <div className="flex flex-row flex-1 overflow-auto">
        {isSidePanelVisible && (
          <div className="w-64 overflow-auto">
            <SidePane />
          </div>
        )}
        <div className="flex-1 bg-gray-100">
          <Outlet />
        </div>
      </div>
      <div>Footer</div>
    </div>
  );
};
