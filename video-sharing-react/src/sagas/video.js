import { all, call, put, takeLatest } from "redux-saga/effects";

import { ActionTypes } from "actions/video/type";
import { request } from "utils/client";

/**
 * Login
 */
export function* fetch(param) {
  try {
    const payload = {
      method: "GET"
    };
    const paramPayload = param.payload;
    const url = `/videos?page_index=${paramPayload.page_index}`;
    const response = yield call(request, url, payload);

    yield put({
      type: ActionTypes.VIDEO_FETCH_SUCCESS,
      payload: response
    });
  } catch (err) {
    /* istanbul ignore next */
    yield put({
      type: ActionTypes.VIDEO_FETCH_FAILURE,
      payload: err
    });
  }
}

/**
 * User Sagas
 */
export default function* root() {
  yield all([takeLatest(ActionTypes.VIDEO_FETCH, fetch)]);
}
