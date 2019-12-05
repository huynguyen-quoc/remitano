import { create, logout } from "actions";

import MainScreen from "components/screens/Main";
import NotFoundScreen from "containers/screens/NotFound";
import React from "react";
import { bindActionCreators } from "redux";
import { shallow } from "enzyme";

describe("Main Screen", () => {
  const mockDispatching = jest.fn();
  const authenticateMock = bindActionCreators(create, mockDispatching);
  const logoutMock = bindActionCreators(logout, mockDispatching);
  const userMock = {
    isAuthenticated: false,
    info: { email: "test" }
  };
  const wrapper = shallow(
    <MainScreen
      user={userMock}
      create={authenticateMock}
      logout={logoutMock}
      notFoundComponent={NotFoundScreen}
    />
  );

  it("should render properly", () => {
    expect(wrapper).toMatchSnapshot();
  });
});
