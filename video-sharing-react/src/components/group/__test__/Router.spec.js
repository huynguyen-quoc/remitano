import HomeScreen from "containers/screens/Home";
import { MemoryRouter } from "react-router-dom";
import NotFoundScreen from "containers/screens/NotFound";
import { Provider } from "react-redux";
import React from "react";
import Router from "components/group/Router";
import configureMockStore from "redux-mock-store";
import { AppRoutes as routes } from "configurations";
import { shallow } from "enzyme";

describe("Router", () => {
  const mockStore = configureMockStore();
  const store = mockStore({
    videos: {
      data: [],
      page: {
        total_pages: 1,
        has_next: false,
        has_previous: false,
        current_page: 0,
        total_elements: 0
      }
    }
  });
  it("should redirect for unauthenticated access", () => {
    const render = shallow(
      <Provider store={store}>
        <MemoryRouter initialEntries={["/private"]}>
          <Router
            routes={routes}
            notFoundComponent={NotFoundScreen}
            homeComponent={HomeScreen}
            isAuthenticated={false}
          />
        </MemoryRouter>
      </Provider>
    );

    expect(render).toMatchSnapshot();
  });

  it("should redirect for authenticated access", () => {
    const render = shallow(
      <Provider store={store}>
        <MemoryRouter initialEntries={["/private"]}>
          <Router
            routes={routes}
            notFoundComponent={NotFoundScreen}
            homeComponent={HomeScreen}
            isAuthenticated={true}
          />
        </MemoryRouter>
      </Provider>
    );

    expect(render).toMatchSnapshot();
  });
});
