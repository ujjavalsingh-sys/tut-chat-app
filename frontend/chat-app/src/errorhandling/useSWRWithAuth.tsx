import { useDispatch } from "react-redux";
import { showErrorMessageToast } from "../store/messageToast/messageToastSlice";
import { useEffect } from "react";
import useSWR from "swr";
import { useNavigate } from "react-router";
import { InvalidAccessTokenError } from "../api/InvalidAccessTokenError";

export const useSWRWithAuth = <T,>(
  key: string,
  fetcher: (url: string) => Promise<T>
) => {
  const { data, error, isLoading } = useSWR(key, fetcher);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  useEffect(() => {
    if (error) {
      dispatch(showErrorMessageToast(error.message));
      if (error instanceof InvalidAccessTokenError) {
        navigate("/login");
      }
    }
  }, [error, dispatch, navigate]);

  return { data, error, isLoading };
};
