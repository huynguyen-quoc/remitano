import React from "react";
import VideoPlayer from "components/screens/Home/components/VideoPlayer";
import { shallow } from "enzyme";

describe("Main Screen", () => {
  const wrapper = shallow(<VideoPlayer url={"test"} />);

  it("should render properly", () => {
    expect(wrapper).toMatchSnapshot();
  });
});
