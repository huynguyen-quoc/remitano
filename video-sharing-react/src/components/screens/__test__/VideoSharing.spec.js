import React from "react";
import VideoSharingScreen from "components/screens/VideoSharing";
import { bindActionCreators } from "redux";
import { shallow } from "enzyme";
import { shareVideo } from "actions";

describe("Video Screen", () => {
  const mockDispatching = jest.fn();
  const shareVideoMock = bindActionCreators(shareVideo, mockDispatching);
  const userMock = {
    isAuthenticated: false,
    info: { email: "test", token: "test" }
  };
  const wrapper = shallow(
    <VideoSharingScreen shareVideo={shareVideoMock} user={userMock} />
  );

  it("should render properly", () => {
    expect(wrapper).toMatchSnapshot();
  });

  it("should dispatch an share action when handle submit", () => {
    const event = { preventDefault: () => {} };
    wrapper.instance().handleSubmit(event);
    expect(mockDispatching).toHaveBeenCalledWith({
      payload: {
        userToken: "test",
        videoUrl: ""
      },
      type: "VIDEO_SHARING"
    });
  });

  it("should change state of videoUrl when handle change", () => {
    const event = {
      preventDefault: () => {},
      target: {
        name: "videoUrl",
        value: "value"
      }
    };
    wrapper.instance().handleChange(event);
    expect(wrapper.state().videoUrl).toEqual("value");
  });
});
