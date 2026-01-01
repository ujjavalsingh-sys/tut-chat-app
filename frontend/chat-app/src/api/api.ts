import { getRequest, postRequest } from "./request";
import type { Person, RegisterForm, LoginForm } from "./types";

export const api_url = import.meta.env.VITE_API_SERVICE_URL;

export const fetchUsers = async (): Promise<Person[]> => {
  return getRequest<Person[]>(`${api_url}/api/users`);
};

export const registerUser = async (
  requestData: RegisterForm
): Promise<Person> => {
  return postRequest<Person>(`${api_url}/api/users/register`, requestData);
};

export const loginUser = async (requestData: LoginForm): Promise<Person> => {
  return postRequest<Person>(`${api_url}/api/users/login`, requestData);
};
