import { api_url } from "./usersClient";
import { getRequest, postRequest } from "./request";
import type { Message, CreateMessageRequest } from "./types";

const convertDate = (message: Message): Message => ({
  ...message,
  creationDate: new Date(message.creationDate),
});

export const sendMessageRequest = async (
  message: CreateMessageRequest
): Promise<Message> => {
  return postRequest<Message>(`${api_url}/api/messages`, message).then(
    convertDate
  );
};

export const fetchMessages = async (url: string): Promise<Message[]> => {
  return getRequest<Message[]>(`${api_url}/api${url}`).then((messages) =>
    messages.map(convertDate)
  );
};
