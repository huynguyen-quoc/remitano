import { MemoryRouter } from "react-router-dom";
import NotFoundScreen from "containers/screens/NotFound";
import React from "react";
import Router from "components/group/Router";
import { renderToString } from "react-dom/server";
import { AppRoutes as routes } from "configurations";

describe("Router", () => {
  it("should redirect for unauthenticated access", () => {
    const render = renderToString(
      <MemoryRouter initialEntries={["/private"]}>
        <Router
          routes={routes}
          notFoundComponent={NotFoundScreen}
          isAuthenticated={false}
        />
      </MemoryRouter>
    );

    expect(render).toMatchSnapshot();
  });

  it("should redirect for authenticated access", () => {
    const render = renderToString(
      <MemoryRouter initialEntries={["/private"]}>
        <Router
          routes={routes}
          notFoundComponent={NotFoundScreen}
          isAuthenticated={true}
        />
      </MemoryRouter>
    );

    expect(render).toMatchSnapshot();
  });
});
