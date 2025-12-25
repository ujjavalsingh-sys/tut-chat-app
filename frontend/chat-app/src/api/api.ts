const api_url = import.meta.env.VITE_API_SERVICE_URL;

export const fetchUsers = async (path: string) => {
    const res = await fetch(`${api_url}${path}`);
    return res.json();
}

export const registerUser = async (username: string, password: string) => {
    const res = await fetch(`${api_url}/api/users/register`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username,
            password
        })
    });
    return res.json();
}

export const loginUser = async (username: string, password: string) => {
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
    return res.json();
}
