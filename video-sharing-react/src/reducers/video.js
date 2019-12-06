import { ActionTypes } from "actions/video/type";
import { AppStatus } from "configurations";
import { handleActions } from "redux-actions";
import immutable from "immutability-helper";

export const videoState = {
  data: [],
  page: {
    total_pages: 1,
    has_next: false,
    has_previous: false,
    current_page: 0,
    total_elements: 0
  }
};

export default {
  videos: handleActions(
    {
      [ActionTypes.VIDEO_FETCH]: (state, { payload }) =>
        immutable(state, {
          data: { $set: payload.page_index === 0 ? [] : state.data },
          page: {
            $set:
              payload.page_index === 0
                ? {
                    total_pages: 1,
                    has_next: false,
                    has_previous: false,
                    current_page: 0,
                    total_elements: 0
                  }
                : state.page
          }
        }),
      [ActionTypes.VIDEO_FETCH_SUCCESS]: (state, { payload }) =>
        immutable(state, {
          data: { $set: [...state.data, ...payload.data] },
          page: { $set: payload.page }
        }),
      [ActionTypes.VIDEO_FETCH_FAILURE]: (state, { payload }) =>
        immutable(state, {
          status: { $set: AppStatus.READY }
        })
    },
    videoState
  )
};
