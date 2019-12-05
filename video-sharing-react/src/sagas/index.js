import { all, fork } from "redux-saga/effects";

import user from "./user";
import video from "./video";

/**
 * rootSaga
 */
export default function* root() {
  yield all([fork(user), fork(video)]);
}
