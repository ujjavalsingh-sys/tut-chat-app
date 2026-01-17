import type { ReactNode } from "react";
import { Navigate } from "react-router";
import { useCheckAuth } from "./useCheckAuth";

export const PublicRoute = ({ children }: { children: ReactNode }) => {
  const { isLoading, loadError, user } = useCheckAuth();
  if (!user) {
    if (loadError) return children;
    return (
      <div className="text-xl">
        <h1>Chat On</h1>
        {isLoading && <progress className="progress w-56"></progress>}
      </div>
    );
  }
  return <Navigate to="/dashboard" replace />;
};
