import { api_url } from "./api";
import { postRequest } from "./request";
import type { Message, MessageRequest } from "./types";

export const sendMessageRequest = async (
  message: MessageRequest
): Promise<Message> => {
  return postRequest(`${api_url}/api/messages`, message);
};
