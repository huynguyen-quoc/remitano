import { all, call, put, takeLatest } from "redux-saga/effects";

import { ActionTypes } from "actions/user/type";
import jwt_decode from "jwt-decode";
import { request } from "utils/client";

/**
 * Login
 */
export function* authenticate(param) {
  try {
    const payload = { method: "POST", ...param };
    const response = yield call(request, "/token", payload);
    const { token } = response;
    const user = jwt_decode(token);
    yield put({
      type: ActionTypes.USER_LOGIN_SUCCESS,
      payload: { token: token, email: user.email, id: user.id }
    });
  } catch (err) {
    /* istanbul ignore next */
    yield put({
      type: ActionTypes.USER_LOGIN_FAILURE,
      payload: err
    });
  }
}

/**
 * Logout
 */
export function* logout() {
  console.log("logout ok");
  yield put({
    type: ActionTypes.USER_LOGOUT_SUCCESS
  });
}

/**
 * User Sagas
 */
export default function* root() {
  yield all([
    takeLatest(ActionTypes.USER_LOGIN, authenticate),
    takeLatest(ActionTypes.USER_LOGOUT, logout)
  ]);
}
