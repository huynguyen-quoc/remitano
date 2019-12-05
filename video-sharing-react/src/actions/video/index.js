import { ActionTypes } from "./type";
import { createActions } from "redux-actions";

export const { videoFetch: fetch } = createActions({
  [ActionTypes.VIDEO_FETCH]: param => ({ ...param })
});
