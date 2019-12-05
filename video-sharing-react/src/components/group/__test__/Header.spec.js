import { login, logout } from "actions";

import Header from "components/group/Header";
import React from "react";
import { bindActionCreators } from "redux";
import { shallow } from "enzyme";

describe("Header Component", () => {
  const mockDispatching = jest.fn();
  const authenticateMock = bindActionCreators(login, mockDispatching);
  const logoutMock = bindActionCreators(logout, mockDispatching);
  const userMock = {
    info: { email: "test" }
  };
  const wrapper = shallow(
    <Header
      authenticate={authenticateMock}
      logout={logoutMock}
      user={userMock}
    />
  );

  it("should render properly", () => {
    expect(wrapper).toMatchSnapshot();
  });

  it("should render properly with authentication", () => {
    userMock.isAuthenticated = true;
    const wrapperAuthentication = shallow(
      <Header
        authenticate={authenticateMock}
        logout={logoutMock}
        user={userMock}
      />
    );
    expect(wrapperAuthentication).toMatchSnapshot();
  });

  it("should dispatch an login action when handle submit", () => {
    wrapper.setState({ email: "email", password: "password" });
    const event = { preventDefault: () => {} };
    wrapper.instance().handleSubmit(event);
    expect(mockDispatching).toHaveBeenCalledWith({
      payload: { email: "email", password: "password" },
      type: "USER_LOGIN"
    });
  });

  it("should dispatch an logout action when handle logout", () => {
    const event = { preventDefault: () => {} };
    wrapper.instance().handleLogout(event);
    expect(mockDispatching).toHaveBeenCalledWith({
      payload: {},
      type: "USER_LOGOUT"
    });
  });

  it("should change state of email when handle change", () => {
    const event = {
      preventDefault: () => {},
      target: {
        name: "email",
        value: "value"
      }
    };
    wrapper.instance().handleChange(event);
    expect(wrapper.state().email).toEqual("value");
  });

  it("should change state of toggle when handle toggle", () => {
    const event = {
      preventDefault: () => {}
    };
    wrapper.instance().handleToggle(event);
    expect(wrapper.state().toggle).toEqual(true);
  });
});
