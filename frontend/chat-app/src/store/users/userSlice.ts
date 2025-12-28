import { createSlice, type PayloadAction } from "@reduxjs/toolkit";
import type { Person } from "../../api/api";

interface AuthenticatedUser {
  user: Person | null
}

const initialState: AuthenticatedUser = { user: null };

const userSlice = createSlice({
  name: "authenticatedUser",
  initialState,
  reducers: {
    clearAuthenticatedUser: (state) => { state.user = null },
    setAuthenticatedUser: (state, action: PayloadAction<Person> ) => { state.user = action.payload }
  }
});

export const { clearAuthenticatedUser, setAuthenticatedUser } = userSlice.actions;
export const userReducer = userSlice.reducer;