import React, { useState } from "react";
import { loginUser, registerUser } from "../api/api";
import { useErrorHandler } from "../errorhandling/useErrorHandler";
import { useNavigate } from "react-router";
import { useDispatch } from "react-redux";
import { setAuthenticatedUser } from "../store/users/authUserSlice";
import type { LoginForm, RegisterForm } from "../api/types";

type Mode = "login" | "register";
type ApiState = "pending" | "error" | "none";

export const Login: React.FC = () => {
  const [mode, setMode] = useState<Mode>("login");
  const [apiState, setApiState] = useState<ApiState>("none");
  const [loginData, setLoginData] = useState<LoginForm>({
    username: "",
    password: "",
  });
  const [registerData, setRegisterData] = useState<RegisterForm>({
    firstname: "",
    lastname: "",
    username: "",
    password: "",
  });

  const enabled =
    mode == "login"
      ? loginData.username && loginData.password
      : registerData.firstname &&
        registerData.lastname &&
        registerData.username &&
        registerData.password;
  const handleLoginChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setLoginData({ ...loginData, [e.target.name]: e.target.value });
  };

  const handleRegisterChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setRegisterData({ ...registerData, [e.target.name]: e.target.value });
  };

  const { notifyError, clearError } = useErrorHandler();
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      if (apiState === "error") {
        clearError();
      }
      setApiState("pending");
      const authUser =
        mode === "login"
          ? await loginUser(loginData)
          : await registerUser(registerData);
      dispatch(setAuthenticatedUser(authUser));
      setApiState("none");
      navigate("/dashboard");
    } catch (error) {
      setApiState("error");
      if (error instanceof Error) {
        notifyError(error);
      }
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gradient-to-r from-indigo-500 via-purple-500 to-pink-500">
      <div className="bg-white shadow-lg rounded-lg p-8 w-full max-w-md">
        <h1 className="text-2xl font-bold text-center mb-6">
          Chat On {mode === "login" ? "Login" : "Register"}
        </h1>

        <form onSubmit={handleSubmit} className="space-y-4">
          {mode === "register" && (
            <>
              <input
                type="text"
                name="firstname"
                placeholder="First Name"
                value={registerData.firstname}
                onChange={handleRegisterChange}
                className="w-full border rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-indigo-400"
                autoFocus
              />
              <input
                type="text"
                name="lastname"
                placeholder="Last Name"
                value={registerData.lastname}
                onChange={handleRegisterChange}
                className="w-full border rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-indigo-400"
              />
            </>
          )}

          <input
            type="text"
            name="username"
            placeholder="Username"
            value={
              mode === "login" ? loginData.username : registerData.username
            }
            onChange={
              mode === "login" ? handleLoginChange : handleRegisterChange
            }
            className="w-full border rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-indigo-400"
            autoFocus
          />

          <input
            type="password"
            name="password"
            placeholder="Password"
            value={
              mode === "login" ? loginData.password : registerData.password
            }
            onChange={
              mode === "login" ? handleLoginChange : handleRegisterChange
            }
            className="w-full border rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-indigo-400"
          />

          {apiState == "pending" ? (
            <div className="w-full bg-indigo-600 text-white py-2 rounded hover:bg-indigo-700 transition text-center">
              <span className="loading loading-dots loading-md"></span>
            </div>
          ) : (
            <button
              type="submit"
              className="w-full btn btn-primary"
              disabled={!enabled}
            >
              {mode === "login" ? "Login" : "Register"}
            </button>
          )}
        </form>

        <div className="text-center mt-4">
          {mode === "login" ? (
            <p>
              Don't have an account?{" "}
              <button
                onClick={() => setMode("register")}
                className="text-indigo-600 hover:underline"
              >
                Register
              </button>
            </p>
          ) : (
            <p>
              Already have an account?{" "}
              <button
                onClick={() => setMode("login")}
                className="text-indigo-600 hover:underline"
              >
                Login
              </button>
            </p>
          )}
        </div>
      </div>
    </div>
  );
};
