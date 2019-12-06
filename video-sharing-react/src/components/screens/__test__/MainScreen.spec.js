import { clearMessage, create, logout } from "actions";

import HomeScreen from "components/screens/Home";
import MainScreen from "components/screens/Main";
import NotFoundScreen from "containers/screens/NotFound";
import React from "react";
import { bindActionCreators } from "redux";
import { shallow } from "enzyme";

describe("Main Screen", () => {
  const mockDispatching = jest.fn();
  const authenticateMock = bindActionCreators(create, mockDispatching);
  const logoutMock = bindActionCreators(logout, mockDispatching);
  const clearMessageMock = bindActionCreators(clearMessage, mockDispatching);
  const userMock = {
    isAuthenticated: false,
    info: { email: "test" }
  };
  const appMock = {
    status: "",
    message: { type: "test", content: "test" }
  };
  const wrapper = shallow(
    <MainScreen
      user={userMock}
      app={appMock}
      clearMessage={clearMessageMock}
      create={authenticateMock}
      logout={logoutMock}
      notFoundComponent={NotFoundScreen}
      homeComponent={HomeScreen}
    />
  );

  it("should render properly", () => {
    expect(wrapper).toMatchSnapshot();
  });

  it("should dispatch an clear message action when handle handleAlertCloseClick", () => {
    const event = {
      preventDefault: () => {}
    };
    wrapper.instance().handleAlertCloseClick(event);
    expect(mockDispatching).toHaveBeenCalledWith({
      payload: {},
      type: "CLEAR_MESSAGE"
    });
  });
});
