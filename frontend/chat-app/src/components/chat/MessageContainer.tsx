import { type ReactNode, useRef } from "react";
import { useDispatch } from "react-redux";
import {
  clearMessageToast,
  showErrorMessageToast,
} from "../../store/messageToast/messageToastSlice";

interface MessageContainerProps {
  children: ReactNode;
  sendMessage: (message: string) => Promise<void>;
}

export const MessageContainer = ({
  children,
  sendMessage,
}: MessageContainerProps) => {
  const messageRef = useRef<HTMLInputElement | null>(null);

  const dispatch = useDispatch();

  const handleSendMessage = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (messageRef.current) {
      const message = messageRef.current.value;
      try {
        await sendMessage(message);
        messageRef.current.value = "";
        dispatch(clearMessageToast());
      } catch (e) {
        if (e instanceof Error) {
          dispatch(showErrorMessageToast(e.message));
        }
      }
    }
  };

  return (
    <form className="flex flex-col h-full" onSubmit={handleSendMessage}>
      <div className="relative h-full flex flex-1 justify-center overflow-auto">
        <div className="absolute inset-0 w-full h-full overflow-hidden pointer-events-none">
          <Background />
        </div>
        <div className="relative z-10 flex-1 overflow-auto w-full">
          {children}
        </div>
      </div>
      <div className="flex flex-row bg-base-100 shadow-sm p-1">
        <input
          type="text"
          className="input bordered flex-1"
          placeholder="Type message"
          ref={messageRef}
        />
        <button className="btn btn-primary" type="submit">
          Send
        </button>
      </div>
    </form>
  );
};

// prettier-ignore
const Background = () => (
  <>
    <img className="w-full h-full object-cover object-top" src="/eiffel.jpg" />
  </>
);
