export const MessageList = () => {
    return (
        <form className="flex flex-col h-full">
            <div className="h-full flex flex-1 justify-center overflow-auto">
                No Messages
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