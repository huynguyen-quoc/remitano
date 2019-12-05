import { ActionTypes } from "actions/user/type";
import reducer from "reducers";

describe("User Reducers", () => {
  it("should return the initial state", () => {
    expect(reducer.user(undefined, {})).toMatchSnapshot();
  });

  it(`should handle ${ActionTypes.USER_LOGIN}`, () => {
    expect(
      reducer.user(undefined, { type: ActionTypes.USER_LOGIN })
    ).toMatchSnapshot();
  });

  it(`should handle ${ActionTypes.USER_LOGIN_SUCCESS}`, () => {
    const actions = {
      type: ActionTypes.USER_LOGIN_SUCCESS,
      payload: {
        data: {
          id: 1,
          name: "test",
          permissions: []
        }
      }
    };
    expect(reducer.user(undefined, actions)).toMatchSnapshot();
  });

  it(`should handle ${ActionTypes.USER_LOGOUT}`, () => {
    expect(
      reducer.user(undefined, { type: ActionTypes.USER_LOGOUT })
    ).toMatchSnapshot();
  });

  it(`should handle ${ActionTypes.USER_LOGOUT_SUCCESS}`, () => {
    expect(
      reducer.user(undefined, { type: ActionTypes.USER_LOGOUT_SUCCESS })
    ).toMatchSnapshot();
  });
});
