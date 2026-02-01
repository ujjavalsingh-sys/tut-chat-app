import { InvalidAccessTokenError } from "./InvalidAccessTokenError";
import { api_url } from "./types";

const handleError = async (response: Response) => {
  if (!response.ok) {
    const data = await response.json().catch(() => null);
    if (response.status === 401) {
      throw new InvalidAccessTokenError();
    }
    if (data) {
      throw new Error("Server error: " + (data.message ?? data.error));
    }
    throw new Error(`Network error ${response.status}: ${response.statusText}`);
  }
};

const refreshToken = async (): Promise<any> => {
  const res = await fetch(`${api_url}/api/auth/refresh`, {
    credentials: "include",
    method: "POST"
  });
  await handleError(res);
  return res.json();
}

export const getRequest = async <T>(url: string, retryCount = 1): Promise<T> => {
  for (let i = 0; i <= retryCount; i++ ) {
    const res = await fetch(url, { credentials: "include" });
    try {
      await handleError(res);
      return res.json() as Promise<T>;
    } catch (e) {
      if (e instanceof InvalidAccessTokenError && i < retryCount) {
        await refreshToken();
        continue;
      }
      throw e;
    }
  }
  throw new Error("Exceeded retries");
};

export const postRequest = async <T>(url: string, payload: any, retryCount = 1): Promise<T> => {
  for (let i = 0; i <= retryCount; i++) {
    const res = await fetch(url, {
      credentials: "include",
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    });
    try {
      await handleError(res);
      return res.json() as Promise<T>;
    } catch (e) {
      if (e instanceof InvalidAccessTokenError && retryCount > 0) {
        await refreshToken();
        continue;
      }
      throw e;
    }
  }
  throw new Error("Exceeded retries");
};
