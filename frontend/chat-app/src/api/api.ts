const user_service_url = import.meta.env.VITE_API_SERVICE_URL;

export const fetchUsers = async (path: string) => {
    const res = await fetch(`${user_service_url}${path}`);
    return res.json()
}