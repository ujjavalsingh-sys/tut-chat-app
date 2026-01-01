import { createSlice, type PayloadAction } from "@reduxjs/toolkit";
import type { Person } from "../../api/types";

interface AuthenticatedUser {
  user: Person | null;
}

const initialState: AuthenticatedUser = { user: null };

const authUserSlice = createSlice({
  name: "authenticatedUser",
  initialState,
  reducers: {
    clearAuthenticatedUser: (state) => {
      state.user = null;
    },
    setAuthenticatedUser: (state, action: PayloadAction<Person>) => {
      state.user = action.payload;
    },
  },
});

export const { clearAuthenticatedUser, setAuthenticatedUser } =
  authUserSlice.actions;
export const authUserReducer = authUserSlice.reducer;
