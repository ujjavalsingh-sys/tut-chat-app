import { ChatBubbleOvalLeftIcon, UserIcon } from "@heroicons/react/16/solid";
import { UserList } from "./UserList";

export const SidePane = () => {
  return (
    <div className="h-full flex flex-col">
      <div className="flex-1 overflow-auto shadow-md">
        <UserList />
      </div>
      <div role="tablist" className="tabs tabs-border">
        <a role="tab" className="tab">
          <ChatBubbleOvalLeftIcon className="h-5 w-5 text-gray-400" />
        </a>
        <a role="tab" className="tab tab-active">
          <UserIcon className="h-5 w-5" />
        </a>
      </div>
    </div>
  );
};
