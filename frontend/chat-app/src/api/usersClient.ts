import { getRequest } from "./request";
import { type Person, api_url } from "./types";

export const fetchUsers = async (): Promise<Person[]> => {
  return getRequest<Person[]>(`${api_url}/api/users`);
};

export const fetchMe = async (url: string): Promise<Person> => {
  return getRequest<Person>(`${api_url}/api${url}`);
};
