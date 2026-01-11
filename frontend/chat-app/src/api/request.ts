const handleError = async (response: Response) => {
  if (!response.ok) {
    const data = await response.json().catch(() => null);
    if (data) {
      throw new Error("Server error: " + (data.message ?? data.error));
    }
    throw new Error(`Network error ${response.status}: ${response.statusText}`);
  }
};

export const getRequest = async <T>(url: string): Promise<T> => {
  const res = await fetch(url, { credentials: "include" });
  await handleError(res);
  return res.json() as Promise<T>;
};

export const postRequest = async <T>(url: string, payload: any): Promise<T> => {
  const res = await fetch(url, {
    credentials: "include",
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(payload),
  });
  await handleError(res);
  return res.json() as Promise<T>;
};
