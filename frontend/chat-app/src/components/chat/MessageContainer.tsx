import { type ReactNode, useRef } from "react";
import { useErrorHandler } from "../../errorhandling/useErrorHandler";

interface MessageContainerProps {
  children: ReactNode;
  sendMessage: (message: string) => Promise<void>;
}

export const MessageContainer = ({
  children,
  sendMessage,
}: MessageContainerProps) => {
  const messageRef = useRef<HTMLInputElement | null>(null);
  const { clearError, notifyError } = useErrorHandler();

  const handleSendMessage = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (messageRef.current) {
      const message = messageRef.current.value;
      try {
        clearError();
        await sendMessage(message);
        messageRef.current.value = "";
      } catch (e) {
        if (e instanceof Error) {
          notifyError(e);
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
