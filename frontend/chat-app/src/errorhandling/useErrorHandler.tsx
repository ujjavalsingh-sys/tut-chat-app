import React from "react";
import { ErrorContext } from "./ErrorProvider";

export const useErrorHandler = () => {
  const ctx = React.useContext(ErrorContext);
  if (!ctx)
    throw new Error("useErrorHandler must be used within ErrorProvider");
  return ctx;
};
