import { ActionTypes } from "./type";
import { createActions } from "redux-actions";

export const { videoFetch: fetch, videoSharing: shareVideo } = createActions({
  [ActionTypes.VIDEO_FETCH]: param => ({ ...param }),
  [ActionTypes.VIDEO_SHARING]: param => ({ ...param })
});
