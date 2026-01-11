import { type ReactNode } from "react";
import { Navigate } from "react-router";
import { useCheckAuth } from "./useCheckAuth";

export const ProtectedRoute = ({ children }: { children: ReactNode }) => {
  const { isLoading, loadError, user } = useCheckAuth({ showError: true });

  if (!user)
    return (
      <div className="text-xl">
        <h1>Chat On: verifying credentials</h1>
        {isLoading && <progress className="progress w-56"></progress>}
        {loadError && <Navigate to="/login" replace />}
      </div>
    );
  return children;
};
