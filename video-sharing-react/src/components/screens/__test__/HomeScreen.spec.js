import HomeScreen from "components/screens/Home";
import React from "react";
import { bindActionCreators } from "redux";
import { fetch } from "actions";
import { shallow } from "enzyme";

describe("Main Screen", () => {
  const mockDispatching = jest.fn();
  const fetchMock = bindActionCreators(fetch, mockDispatching);
  const videoMock = {
    data: [
      {
        id: 1,
        url: "test",
        title: "test",
        desription: "test",
        user: { id: 1, email: "test" }
      }
    ],
    page: {
      total_pages: 1,
      has_next: false,
      has_previous: false,
      current_page: 0,
      total_elements: 0
    }
  };
  const wrapper = shallow(<HomeScreen fetch={fetchMock} videos={videoMock} />);

  it("should render properly", () => {
    expect(wrapper).toMatchSnapshot();
  });

  it("should dispatch an fetch action when handle loadMore", () => {
    wrapper.instance().handleLoadMore(1);
    expect(mockDispatching).toHaveBeenCalledWith({
      payload: { page_index: 1 },
      type: "VIDEO_FETCH"
    });
  });
});
