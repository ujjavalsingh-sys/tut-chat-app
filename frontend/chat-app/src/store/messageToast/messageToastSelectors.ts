import type { RootState } from "../store";

export const selectMessageToast = (state: RootState) => state.toast.info;
