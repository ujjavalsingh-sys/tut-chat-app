import { useNavigate } from "react-router";
import { registerUser } from "../api/api";
import { useRef } from "react";

export const Login = () => {
    const navigate = useNavigate()
    const usernameRef = useRef(null);
    const passwordRef = useRef(null);

    const handleLogin = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        navigate("/dashboard");
    };

    const handleRegister = async () => {
        const postResponse = await registerUser(usernameRef.current.value, passwordRef.current.value);
        navigate("/dashboard");
    };

    return (
    <form className="card bg-base-200 shadow-xl p-6 space-y-4 w-96 mx-auto mt-10" 
            onSubmit={handleLogin}>
        <h2 className="text-xl font-bold">Chat On - a tutorial chat app</h2>

        <div className="form-control">
        <label className="label">
            <span className="label-text">Username</span>
        </label>
        <input
            type="text"
            placeholder="Your name"
            className="input input-bordered"
            ref={usernameRef}
            autoFocus
        />
        </div>

        <div className="form-control">
        <label className="label">
            <span className="label-text">Password</span>
        </label>
        <input
            type="password"
            placeholder="password"
            className="input input-bordered"
            ref={passwordRef}
        />
        </div>

        <div className="flex justify-center space-x-2">
        <button type="submit" className="btn btn-primary">
            Login
        </button>
        <button type="button" className="btn" onClick={handleRegister}>
            Register
        </button>
        </div>
    </form>
    )
}