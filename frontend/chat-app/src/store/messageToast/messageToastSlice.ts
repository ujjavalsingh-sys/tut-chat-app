import { createSlice } from "@reduxjs/toolkit";

type MessageToastType = "error";

interface MessageToastInfo {
  message: string;
  type: MessageToastType;
}

interface MessageToast {
  info: MessageToastInfo | undefined;
}

const initialState: MessageToast = { info: undefined };

const slice = createSlice({
  name: "messageToastInfo",
  initialState,
  reducers: {
    showErrorMessageToast: (_, action) => ({
      info: {
        message: action.payload,
        type: "error",
      },
    }),
    clearMessageToast: () => ({ ...initialState }),
  },
});

export const { showErrorMessageToast, clearMessageToast } = slice.actions;
export const messageToastReducer = slice.reducer;
