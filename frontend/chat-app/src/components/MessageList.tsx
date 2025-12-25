export const MessageList = () => {
    const handleSendMessage = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

    }
    return (
        <form className="flex flex-col h-full">
            <div className="relative h-full flex flex-1 justify-center overflow-hidden">
                <div className="absolute flex-1 h-full w-full z-1 flex flex-col items-center overflow-auto">
                    <div className="badge m-2">No messages</div>
                </div>

            </div>
            <div className="flex flex-row bg-base-100 shadow-sm p-1">
                <input type="text"
                    className="input bordered flex-1"
                    placeholder="Type message"/>
                <button className="btn btn-primary">Send</button>
            </div>
        </form>
    )
}