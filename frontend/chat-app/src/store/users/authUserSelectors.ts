import type { RootState } from "../store";

export const selectAuthUserFetchState = (state: RootState) => state.user;
export const selectAuthUser = (state: RootState) => {
  const user = state.user.user;
  if (!user) throw new Error("You're not logged in!");
  return user;
};
export const selectAuthUserId = (state: RootState) => selectAuthUser(state).id;
export const selectAuthUserName = (state: RootState) => {
  const { firstName, lastName } = selectAuthUser(state);
  return `${firstName} ${lastName}`;
};
