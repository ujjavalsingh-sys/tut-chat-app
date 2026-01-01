import { api_url } from "./api";
import { getRequest } from "./request";
import type { Conversation, ConversationSummary } from "./types";

export const fetchConversations = async (
  urlSuffix: string
): Promise<ConversationSummary[]> => {
  return getRequest<ConversationSummary[]>(`${api_url}/api${urlSuffix}`);
};

export const fetchConversation = async (
  urlSuffix: string
): Promise<Conversation> => {
  return getRequest<Conversation>(`${api_url}/api${urlSuffix}`);
};
