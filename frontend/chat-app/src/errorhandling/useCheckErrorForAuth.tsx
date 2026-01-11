import { useDispatch } from "react-redux";
import { useNavigate } from "react-router";
import { showErrorMessageToast } from "../store/messageToast/messageToastSlice";
import { clearAuthenticatedUser } from "../store/users/authUserSlice";
import { useEffect } from "react";

export const useCheckErrorForAuth = (
  error: string | undefined,
  logout: boolean
) => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  useEffect(() => {
    if (error) {
      const hasAccessExpired = error.startsWith("Server error: JWT expired");
      const message = hasAccessExpired
        ? "Session ended, login again to continue"
        : error;
      dispatch(showErrorMessageToast(message));
      if (hasAccessExpired && logout) {
        dispatch(clearAuthenticatedUser());
        navigate("/login");
      }
    }
  }, [error, logout]);
};
