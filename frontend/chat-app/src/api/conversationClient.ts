import { api_url } from "./usersClient";
import { getRequest } from "./request";
import type { Conversation } from "./types";

export const fetchConversations = async (
  urlSuffix: string
): Promise<Conversation[]> => {
  return getRequest<Conversation[]>(`${api_url}/api${urlSuffix}`);
};

export const fetchConversation = async (
  urlSuffix: string
): Promise<Conversation> => {
  return getRequest<Conversation>(`${api_url}/api${urlSuffix}`);
};
