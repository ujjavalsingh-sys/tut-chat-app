import { useMemo } from "react";
import type { Message, ParticipantInfo } from "../../api/types";

interface ChatBubbleProps {
  message: Message;
  participants: ParticipantInfo[];
  authUserId: number;
}
export const ChatBubble = ({
  message,
  participants,
  authUserId,
}: ChatBubbleProps) => {
  const sender = useMemo(
    () => participants.find(({ id }) => id == message.senderId),
    [participants, message.senderId]
  ) as ParticipantInfo;
  const isMyMessage = message.senderId == authUserId;
  return (
    <div className={`px-5 chat ${isMyMessage ? "chat-end" : "chat-start"}`}>
      <div className="chat-image avatar">
        <div className="w-10 rounded-full border-1 flex justify-center items-center">
          {/* <img
            alt="Tailwind CSS chat bubble component"
            src="https://img.daisyui.com/images/profile/demo/kenobee@192.webp"
          /> */}
          <div className="text-l font-thin opacity-70 tabular-nums">
            {sender.firstName[0]}
            {sender.lastName[0]}
          </div>
        </div>
      </div>
      <div className="chat-header">
        {sender.firstName} {sender.lastName}
        <time className="text-xs opacity-50">
          {message.creationDate.toLocaleTimeString("en-US", {
            timeStyle: "short",
          })}
        </time>
      </div>
      <div className={`chat-bubble ${isMyMessage ? "bg-blue-100" : ""}`}>
        {message.message}
      </div>
      {/* <div className="chat-footer opacity-50">Delivered</div> */}
    </div>
  );
};
