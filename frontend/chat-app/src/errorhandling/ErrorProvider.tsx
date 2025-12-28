import React, { useState, type ReactNode } from "react";
import { ErrorToast } from "./ErrorToast";

type ErrorContextData = {
  notifyError: (error: Error) => void;
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

  return (
    <>
      <ErrorToast errorMessage={errorMessage} />
      <ErrorContext.Provider value={{ notifyError }}>
        {children}
      </ErrorContext.Provider>
    </>
  );
};
