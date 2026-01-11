import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import type { Person } from "../../api/types";
import { fetchMe } from "../../api/usersClient";

interface AuthUserFetchState {
  isLoading: boolean;
  loadError: string | undefined;
  user: Person | undefined;
}

const initialState: AuthUserFetchState = {
  isLoading: false,
  loadError: undefined,
  user: undefined,
};

export const fetchMeThunk = createAsyncThunk(
  "/users/me",
  async (_, { rejectWithValue }) => {
    try {
      return await fetchMe("/users/me");
    } catch (e) {
      if (e instanceof Error) {
        return rejectWithValue(e.message);
      }
    }
  }
);

const authUserSlice = createSlice({
  name: "authenticatedUser",
  initialState,
  reducers: {
    clearAuthenticatedUser: () => ({ ...initialState }),
    setAuthenticatedUser: (_state, action) => ({
      isLoading: false,
      loadError: undefined,
      user: action.payload,
    }),
  },
  extraReducers: ({ addCase }) => {
    addCase(fetchMeThunk.pending, () => ({
      isLoading: true,
      loadError: undefined,
      user: undefined,
    }));
    addCase(fetchMeThunk.fulfilled, (_, action) => ({
      isLoading: false,
      loadError: undefined,
      user: action.payload,
    }));
    addCase(fetchMeThunk.rejected, (_, action) => ({
      isLoading: false,
      loadError: action.payload as string,
      user: undefined,
    }));
  },
});

export const { clearAuthenticatedUser, setAuthenticatedUser } =
  authUserSlice.actions;
export const authUserReducer = authUserSlice.reducer;
