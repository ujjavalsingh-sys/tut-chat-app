import { Outlet } from "react-router";
import { useState } from "react";
import { SidePane } from "../sidepane/SidePane";
import {
  ArrowLeftStartOnRectangleIcon,
  Bars3Icon,
} from "@heroicons/react/16/solid";
import { useDispatch, useSelector } from "react-redux";
import { selectAuthUserName } from "../../store/users/authUserSelectors";
import { logoutUser } from "../../api/authClient";
import { clearAuthenticatedUser } from "../../store/users/authUserSlice";
import { showErrorMessageToast } from "../../store/messageToast/messageToastSlice";

export const Dashboard = () => {
  const [isSidePanelVisible, setIsSidePanelVisible] = useState(true);

  const authUserName = useSelector(selectAuthUserName);
  const dispatch = useDispatch();

  const handleToggleSidePanel = () => {
    setIsSidePanelVisible(!isSidePanelVisible);
  };

  const handleLogout = async () => {
    try {
      const loggedOutUser = await logoutUser();
      dispatch(clearAuthenticatedUser());
      dispatch(
        showErrorMessageToast(
          `${loggedOutUser.firstName} ${loggedOutUser.lastName} has been signed out.`,
        ),
      );
    } catch (e) {
      if (e instanceof Error) {
        dispatch(showErrorMessageToast(e.message));
      }
    }
  };
  return (
    <div className="flex h-full w-full flex-col">
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
          <div className="w-64">
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
