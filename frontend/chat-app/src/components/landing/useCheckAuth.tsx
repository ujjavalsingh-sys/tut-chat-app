import { useEffect } from "react";
import { useSelector } from "react-redux";
import { store } from "../../store/store";
import { selectAuthUserFetchState } from "../../store/users/authUserSelectors";
import { fetchMeThunk } from "../../store/users/authUserSlice";

export const useCheckAuth = () => {
  const { isLoading, loadError, user } = useSelector(selectAuthUserFetchState);

  useEffect(() => {
    if (!user) {
      store.dispatch(fetchMeThunk());
    }
  }, [user]);

  return { isLoading, loadError, user };
};
