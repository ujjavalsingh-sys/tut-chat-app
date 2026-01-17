import { Navigate } from "react-router";
import { ProtectedRoute } from "./ProtectedRoute";

export const Landing = () => {
  return (
    <ProtectedRoute>
      <Navigate to="/dashboard" replace />
    </ProtectedRoute>
  );
};
