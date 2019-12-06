import React from "react";
import VideoSharingForm from "components/basic/Form/VideoSharing";
import { mount } from "enzyme";

describe("Main Screen", () => {
  const onChangeMock = jest.fn();
  const wrapper = mount(<VideoSharingForm onChange={onChangeMock} />);
  it("should render properly", () => {
    expect(wrapper).toMatchSnapshot();
  });
});
