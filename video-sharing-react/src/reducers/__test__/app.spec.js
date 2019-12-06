import { ActionTypes as ActionAppTypes } from "actions/app/type";
import { ActionTypes as ActionUserTypes } from "actions/user/type";
import { ActionTypes as ActionVideoTypes } from "actions/video/type";
import reducer from "reducers";

describe("App Reducers", () => {
  it("should return the initial state", () => {
    expect(reducer.user(undefined, {})).toMatchSnapshot();
  });

  it(`should handle ${ActionUserTypes.USER_LOGIN}`, () => {
    expect(
      reducer.app(undefined, { type: ActionUserTypes.USER_LOGIN })
    ).toMatchSnapshot();
  });

  it(`should handle ${ActionAppTypes.CLEAR_MESSAGE}`, () => {
    expect(
      reducer.app(undefined, { type: ActionAppTypes.CLEAR_MESSAGE })
    ).toMatchSnapshot();
  });

  it(`should handle ${ActionUserTypes.USER_LOGIN_FAILURE}`, () => {
    expect(
      reducer.app(undefined, { type: ActionUserTypes.USER_LOGIN_FAILURE })
    ).toMatchSnapshot();
  });

  it(`should handle ${ActionUserTypes.USER_LOGIN_SUCCESS}`, () => {
    const actions = {
      type: ActionUserTypes.USER_LOGIN_SUCCESS,
      payload: {
        data: {
          id: 1,
          name: "test",
          permissions: []
        }
      }
    };
    expect(reducer.app(undefined, actions)).toMatchSnapshot();
  });

  it(`should handle ${ActionVideoTypes.VIDEO_FETCH}`, () => {
    expect(
      reducer.app(undefined, { type: ActionVideoTypes.VIDEO_FETCH })
    ).toMatchSnapshot();
  });
  it(`should handle ${ActionVideoTypes.VIDEO_SHARING}`, () => {
    expect(
      reducer.app(undefined, { type: ActionVideoTypes.VIDEO_SHARING })
    ).toMatchSnapshot();
  });
  it(`should handle ${ActionVideoTypes.VIDEO_SHARING_SUCCESS}`, () => {
    expect(
      reducer.app(undefined, { type: ActionVideoTypes.VIDEO_SHARING_SUCCESS })
    ).toMatchSnapshot();
  });

  it(`should handle ${ActionVideoTypes.VIDEO_SHARING_FAILURE}`, () => {
    expect(
      reducer.app(undefined, { type: ActionVideoTypes.VIDEO_SHARING_FAILURE })
    ).toMatchSnapshot();
  });
});
