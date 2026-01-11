import { configureStore } from "@reduxjs/toolkit";
import { authUserReducer } from "./users/authUserSlice";
import { messageToastReducer } from "./messageToast/messageToastSlice";

export const store = configureStore({
  reducer: {
    user: authUserReducer,
    toast: messageToastReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
