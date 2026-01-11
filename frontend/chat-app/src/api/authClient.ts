import { postRequest } from "./request";
import {
  type NewPersonRequest,
  type Person,
  api_url,
  type LoginRequest,
} from "./types";

export const registerUser = async (
  requestData: NewPersonRequest
): Promise<Person> => {
  return postRequest<Person>(`${api_url}/api/auth/register`, requestData);
};

export const loginUser = async (requestData: LoginRequest): Promise<Person> => {
  return postRequest<Person>(`${api_url}/api/auth/login`, requestData);
};
