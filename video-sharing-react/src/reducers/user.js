import { ActionTypes } from "actions/user/type";
import { AppStatus } from "configurations";
import { handleActions } from "redux-actions";
import immutable from "immutability-helper";

export const userState = {
  isAuthenticated: false,
  info: {
    id: -1,
    email: "",
    token: ""
  }
};

export default {
  user: handleActions(
    {
      [ActionTypes.USER_LOGIN]: state =>
        immutable(state, {
          status: { $set: AppStatus.RUNNING }
        }),
      [ActionTypes.USER_LOGIN_SUCCESS]: (state, { payload }) =>
        immutable(state, {
          isAuthenticated: { $set: true },
          info: {
            id: { $set: payload.id },
            email: { $set: payload.email },
            token: { $set: payload.token }
          },
          status: { $set: AppStatus.READY }
        }),
      [ActionTypes.USER_LOGOUT]: state =>
        immutable(state, {
          status: { $set: AppStatus.RUNNING }
        }),
      [ActionTypes.USER_LOGOUT_SUCCESS]: state =>
        immutable(state, {
          isAuthenticated: { $set: false },
          info: { id: { $set: -1 }, email: { $set: "" }, token: { $set: "" } },
          status: { $set: AppStatus.IDLE }
        })
    },
    userState
  )
};
