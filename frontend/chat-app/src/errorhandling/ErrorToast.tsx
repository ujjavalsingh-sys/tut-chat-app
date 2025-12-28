export interface ErrorBoundaryState {
  errorMessage?: string;
}

export const ErrorToast = ({ errorMessage }: ErrorBoundaryState) => {
  if (errorMessage) {
    return (
      <div className="flex justify-center">
        <div className="absolute top-5 alert alert-error text-gray-100 font-bold">
          {errorMessage}
        </div>
      </div>
    );
  }
  return <></>;
};
