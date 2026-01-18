import { useDispatch } from "react-redux";
import { showErrorMessageToast } from "../store/messageToast/messageToastSlice";
import useSWR from "swr";
import { InvalidAccessTokenError } from "../api/InvalidAccessTokenError";
import { clearAuthenticatedUser } from "../store/users/authUserSlice";

export const useSWRWithAuth = <T,>(
  key: string,
  fetcher: (url: string) => Promise<T>,
) => {
  const dispatch = useDispatch();
  const { data, error, isLoading } = useSWR(key, fetcher, {
    onError: (err) => {
      if (err instanceof InvalidAccessTokenError) {
        dispatch(showErrorMessageToast(err.message));
        dispatch(clearAuthenticatedUser());
      }
    },
  });

  return { data, error, isLoading };
};
