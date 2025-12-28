const api_url = import.meta.env.VITE_API_SERVICE_URL;

export interface Person {
    id: number,
    username: string,
    firstName: string,
    lastName: string
};

export const fetchUsers = async (path: string): Promise<Person[]> => {
    const res = await fetch(`${api_url}${path}`);
    return res.json();
}

export interface Conversation {
    
}

export const fetchConversations = async (userId: number): Promise<Person[]> => {
    const res = await fetch(`${api_url}/users/${userId}/conversations`);
    return res.json();
}

export const registerUser = async (requestData: string): Promise<Person> => {
    const res = await fetch(`${api_url}/api/users/register`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: requestData
    });
    return res.json();
}

export const loginUser = async (username: string, password: string): Promise<Person> => {
    const res = await fetch(`${api_url}/api/users/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username,
            password
        })
    });
    if (!res.ok) {
        throw new Error(`Login error: ${res.status} ${res.statusText}`);
    }
    return res.json();
}
