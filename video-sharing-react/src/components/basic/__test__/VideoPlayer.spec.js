import React from "react";
import VideoPlayer from "components/basic/VideoPlayer";
import { mount } from "enzyme";

describe("Main Screen", () => {
  const wrapper = mount(<VideoPlayer url={"test"} />);

  it("should render properly", () => {
    expect(wrapper).toMatchSnapshot();
  });
});
