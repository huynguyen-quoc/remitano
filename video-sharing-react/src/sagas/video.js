import { all, call, put, takeLatest } from "redux-saga/effects";

import { ActionTypes } from "actions/video/type";
import { AppConfig } from "configurations";
import { parseYoutubeId } from "utils/helpers";
import { request } from "utils/client";

/**
 * Fetch Video
 */
export function* fetch(param) {
  try {
    const payload = {
      method: "GET"
    };
    const paramPayload = param.payload;
    const url = `/videos?page_index=${paramPayload.page_index}`;
    const response = yield call(request, url, payload);

    yield put({
      type: ActionTypes.VIDEO_FETCH_SUCCESS,
      payload: response
    });
  } catch (err) {
    /* istanbul ignore next */
    yield put({
      type: ActionTypes.VIDEO_FETCH_FAILURE,
      payload: err
    });
  }
}

/**
 * Fetch Video
 */
export function* share(param) {
  try {
    const { payload } = param;
    const { videoUrl, userToken } = payload;
    const { youtubeAPIKey, youtubeAPIURL } = AppConfig;
    const requestPayload = {
      method: "GET"
    };
    const videoId = parseYoutubeId(videoUrl);

    const url = `${youtubeAPIURL}?part=snippet&id=${videoId}&key=${youtubeAPIKey}`;
    const response = yield call(request, url, requestPayload);

    const { items } = response;
    const { snippet } = items[0];
    const { title, description } = snippet;

    const sharingPayload = {
      title: title,
      description: description,
      url: videoUrl
    };
    const headers = { Authorization: `Bearer ${userToken}` };
    const requestSharingPayload = {
      method: "POST",
      payload: sharingPayload,
      headers: headers
    };
    yield call(request, "/videos", requestSharingPayload);

    yield put({
      type: ActionTypes.VIDEO_SHARING_SUCCESS
    });
  } catch (err) {
    /* istanbul ignore next */
    yield put({
      type: ActionTypes.VIDEO_SHARING_FAILURE,
      payload: err
    });
  }
}

/**
 * Video Sagas
 */
export default function* root() {
  yield all([takeLatest(ActionTypes.VIDEO_FETCH, fetch)]);
  yield all([takeLatest(ActionTypes.VIDEO_SHARING, share)]);
}
