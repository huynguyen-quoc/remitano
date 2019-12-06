import Alert from "components/basic/Alert";
import React from "react";
import { mount } from "enzyme";

describe("Alert", () => {
  const onClick = jest.fn();
  const wrapper = mount(<Alert message={"test"} onClick={onClick} />);
  it("should render properly", () => {
    expect(wrapper).toMatchSnapshot();
  });
});
