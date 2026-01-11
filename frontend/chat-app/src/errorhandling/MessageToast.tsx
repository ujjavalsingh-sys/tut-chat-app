import { useSelector } from "react-redux";
import { selectMessageToast } from "../store/messageToast/messageToastSelectors";

export const MessageToast = () => {
  const messageToast = useSelector(selectMessageToast);

  if (messageToast) {
    const { message } = messageToast;
    return (
      <div className="flex justify-center">
        <div className="absolute z-1 top-5 alert alert-error text-gray-100 font-bold">
          {message}
        </div>
      </div>
    );
  }
  return <></>;
};
