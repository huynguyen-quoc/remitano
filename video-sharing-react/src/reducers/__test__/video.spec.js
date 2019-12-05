import { ActionTypes } from "actions/video/type";
import reducer from "reducers";

describe("Video Reducers", () => {
  it("should return the initial state", () => {
    expect(reducer.videos(undefined, {})).toMatchSnapshot();
  });

  it(`should handle ${ActionTypes.VIDEO_FETCH}`, () => {
    expect(
      reducer.videos(undefined, { type: ActionTypes.VIDEO_FETCH })
    ).toMatchSnapshot();
  });
  it(`should handle ${ActionTypes.VIDEO_FETCH_SUCCESS}`, () => {
    const actions = {
      type: ActionTypes.VIDEO_FETCH_SUCCESS,
      payload: {
        data: [],
        page: {
          total_pages: 1,
          has_next: false,
          has_previous: false,
          current_page: 0,
          total_elements: 0
        }
      }
    };
    expect(reducer.videos(undefined, actions)).toMatchSnapshot();
  });

  it(`should handle ${ActionTypes.VIDEO_FETCH_FAILURE}`, () => {
    expect(
      reducer.videos(undefined, { type: ActionTypes.VIDEO_FETCH_FAILURE })
    ).toMatchSnapshot();
  });
});
