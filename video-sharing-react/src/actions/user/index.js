import { ActionTypes } from "./type";
import { createActions } from "redux-actions";

export const { userLogin: login, userLogout: logout } = createActions({
  [ActionTypes.USER_LOGIN]: param => ({ ...param }),
  [ActionTypes.USER_LOGOUT]: () => ({})
});
