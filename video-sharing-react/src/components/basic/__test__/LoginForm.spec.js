import LoginForm from "components/basic/Form/Login";
import React from "react";
import { shallow } from "enzyme";

describe("Login Form", () => {
  const onChangeMock = jest.fn();
  const wrapper = shallow(<LoginForm onChange={onChangeMock} />);

  it("should render properly", () => {
    expect(wrapper).toMatchSnapshot();
  });
});
