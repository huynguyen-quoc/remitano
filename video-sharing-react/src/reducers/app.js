import { ActionTypes as ActionAppTypes } from "actions/app/type";
import { ActionTypes as ActionUserTypes } from "actions/user/type";
import { ActionTypes as ActionVideoTypes } from "actions/video/type";
import { AppStatus } from "configurations";
import { handleActions } from "redux-actions";
import immutable from "immutability-helper";

export const appState = {
  status: AppStatus.IDLE,
  message: {
    type: "",
    content: ""
  }
};

export default {
  app: handleActions(
    {
      [ActionUserTypes.USER_LOGIN]: state =>
        immutable(state, {
          status: { $set: AppStatus.RUNNING }
        }),
      [ActionAppTypes.CLEAR_MESSAGE]: state =>
        immutable(state, {
          status: { $set: AppStatus.RUNNING },
          message: {
            type: { $set: "" },
            content: { $set: "" }
          }
        }),
      [ActionUserTypes.USER_LOGIN_FAILURE]: state =>
        immutable(state, {
          status: { $set: AppStatus.READY },
          message: {
            type: { $set: "danger" },
            content: { $set: "Login failed!" }
          }
        }),
      [ActionUserTypes.USER_LOGIN_SUCCESS]: state =>
        immutable(state, {
          status: { $set: AppStatus.READY },
          message: {
            type: { $set: "" },
            content: { $set: "" }
          }
        }),
      [ActionVideoTypes.VIDEO_FETCH]: (state, { payload }) =>
        immutable(state, {
          message: {
            type: { $set: "" },
            content: { $set: "" }
          }
        }),
      [ActionVideoTypes.VIDEO_SHARING]: state =>
        immutable(state, {
          status: { $set: AppStatus.RUNNING }
        }),
      [ActionVideoTypes.VIDEO_SHARING_SUCCESS]: (state, { payload }) =>
        immutable(state, {
          message: {
            type: { $set: "success" },
            content: { $set: "Your video has been shared!" }
          },
          status: { $set: AppStatus.READY }
        }),
      [ActionVideoTypes.VIDEO_SHARING_FAILURE]: (state, { payload }) =>
        immutable(state, {
          message: {
            type: { $set: "danger" },
            content: { $set: "There are some problem has been occured!" }
          },
          status: { $set: AppStatus.READY }
        })
    },
    appState
  )
};
