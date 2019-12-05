import * as matchers from "redux-saga-test-plan/matchers";

import video, { fetch } from "sagas/video";

import { ActionTypes } from "actions/video/type";
import { expectSaga } from "redux-saga-test-plan";
import { request } from "utils/client";

describe("User Sagas", () => {
  it("should have the expected watchers", done => {
    expectSaga(video)
      .run({ silenceTimeout: true })
      .then(saga => {
        expect(saga).toMatchSnapshot();
        done();
      });
  });

  it("should match the fetch saga", () => {
    const fakeDataReturn = {
      data: [],
      page: {
        total_pages: 1,
        has_next: false,
        has_previous: false,
        current_page: 0,
        total_elements: 0
      }
    };
    expectSaga(fetch, { payload: { page_index: 1 } })
      .provide([[matchers.call.fn(request), fakeDataReturn]])
      .put({
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
      })
      .run();
  });
});
