import type { RootState } from "../store";

export const selectAuthUserName = (state: RootState) => {
  const user = state.user.user;
  return user && `${user?.firstName} ${user?.lastName}`;
};

export const selectAuthUserId = (state: RootState) => state.user.user?.id;
export const selectAuthUser = (state: RootState) => state.user.user;
