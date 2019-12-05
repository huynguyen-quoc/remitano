import * as matchers from "redux-saga-test-plan/matchers";

import user, { authenticate, create, logout } from "sagas/user";

import { ActionTypes } from "actions/user/type";
import { expectSaga } from "redux-saga-test-plan";
import { request } from "utils/client";

describe("User Sagas", () => {
  it("should have the expected watchers", done => {
    expectSaga(user)
      .run({ silenceTimeout: true })
      .then(saga => {
        expect(saga).toMatchSnapshot();
        done();
      });
  });

  it("should match the authenticate saga", () => {
    const fakeToken = {
      type: "bearer",
      token:
        "eyJhbGciOiJIUzUxMiJ9.eyJpZCI6MSwiZXhwIjoxNTc1NTQ4Nzc3LCJpYXQiOjE1NzU1MzA3NzcsImVtYWlsIjoidGVzdDJAZ21haWwuY29tIn0.RClf9eFWY6hKoKqhm6RWY8PCXlAEj6eNNk8YOtbbZMzjF_vpp_wZc3a2aUddQYXHTMJ-1jO3_Agf76Zani9ETg"
    };
    expectSaga(authenticate)
      .provide([[matchers.call.fn(request), fakeToken]])
      .put({
        type: ActionTypes.USER_LOGIN_SUCCESS,
        payload: {
          token:
            "eyJhbGciOiJIUzUxMiJ9.eyJpZCI6MSwiZXhwIjoxNTc1NTQ4Nzc3LCJpYXQiOjE1NzU1MzA3NzcsImVtYWlsIjoidGVzdDJAZ21haWwuY29tIn0.RClf9eFWY6hKoKqhm6RWY8PCXlAEj6eNNk8YOtbbZMzjF_vpp_wZc3a2aUddQYXHTMJ-1jO3_Agf76Zani9ETg",
          email: "test2@gmail.com",
          id: 1
        }
      })
      .run();
  });

  it("should match the create saga", () => {
    const fakeUser = {
      id: 1,
      email: "test@gmail"
    };
    expectSaga(create, { payload: { email: "email", password: "password" } })
      .provide([[matchers.call.fn(request), fakeUser]])
      .put({
        type: ActionTypes.USER_LOGIN,
        payload: { email: "email", password: "password" }
      })
      .run();
  });

  it("should match the logout saga", () =>
    expectSaga(logout)
      .put({
        type: ActionTypes.USER_LOGOUT_SUCCESS
      })
      .run());
});
