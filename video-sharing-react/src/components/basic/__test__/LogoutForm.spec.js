import LogutForm from "components/basic/Form/Logout";
import React from "react";
import { shallow } from "enzyme";

describe("Logout Form", () => {
  const onLogoutMock = jest.fn();
  const mockUser = {
    info: { email: "test" }
  };
  const wrapper = shallow(
    <LogutForm onLogout={onLogoutMock} user={mockUser} />
  );

  it("should render properly", () => {
    expect(wrapper).toMatchSnapshot();
  });
});
