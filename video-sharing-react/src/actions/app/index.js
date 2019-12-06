import { ActionTypes } from "./type";
import { createActions } from "redux-actions";

export const { clearMessage } = createActions({
  [ActionTypes.CLEAR_MESSAGE]: param => ({ ...param })
});
