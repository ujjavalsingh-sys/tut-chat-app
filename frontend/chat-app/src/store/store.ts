import { configureStore } from "@reduxjs/toolkit";
import { authUserReducer } from "./users/authUserSlice";

export const store = configureStore({
  reducer: {
    user: authUserReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
