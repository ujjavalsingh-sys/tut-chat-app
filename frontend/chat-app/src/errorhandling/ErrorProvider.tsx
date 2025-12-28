import React, { useState, type ReactNode } from "react";
import { ErrorToast } from "./ErrorToast";

type ErrorContextData = {
  notifyError: (error: Error) => void;
  clearError: () => void;
};

export const ErrorContext = React.createContext<ErrorContextData | null>(null);

export const ErrorProvider: React.FC<{ children: ReactNode }> = ({
  children,
}) => {
  const [errorMessage, setErrorMessage] = useState<string | undefined>(
    undefined
  );

  const notifyError = (error: Error) => {
    setErrorMessage(error.message);
  };

  const clearError = () => {
    setErrorMessage(undefined);
  };

  return (
    <>
      <ErrorToast errorMessage={errorMessage} />
      <ErrorContext.Provider value={{ notifyError, clearError }}>
        {children}
      </ErrorContext.Provider>
    </>
  );
};
