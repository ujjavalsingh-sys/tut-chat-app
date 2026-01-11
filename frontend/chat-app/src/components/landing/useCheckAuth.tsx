import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { store } from "../../store/store";
import { selectAuthUserFetchState } from "../../store/users/authUserSelectors";
import { fetchMeThunk } from "../../store/users/authUserSlice";
import { showErrorMessageToast } from "../../store/messageToast/messageToastSlice";

export const useCheckAuth = ({ showError }: { showError: boolean }) => {
  const { isLoading, loadError, user } = useSelector(selectAuthUserFetchState);

  useEffect(() => {
    if (!user) {
      store.dispatch(fetchMeThunk());
    }
  }, [user]);

  const dispatch = useDispatch();

  useEffect(() => {
    if (showError && loadError) {
      dispatch(showErrorMessageToast(loadError));
    }
  }, [loadError, showError]);

  return { isLoading, loadError, user };
};
