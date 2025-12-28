import type { RootState } from "../store";

export const selectAuthUser = (state: RootState) => state.user.user;
export const selectAuthUserId = (state: RootState) => state.user.user?.id;