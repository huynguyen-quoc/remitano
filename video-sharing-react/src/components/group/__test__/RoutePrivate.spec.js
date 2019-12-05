import { MemoryRouter } from "react-router-dom";
import NotFoundScreen from "containers/screens/NotFound";
import React from "react";
import RoutePrivate from "components/group/Router/RoutePrivate";
import { renderToString } from "react-dom/server";
import { AppRoutes as routes } from "configurations";

describe("Route Private", () => {
  it("should redirect for unauthenticated access", () => {
    const render = renderToString(
      <MemoryRouter initialEntries={["/private"]}>
        <RoutePrivate
          key={"test"}
          path={"test"}
          to={"test"}
          isAuthenticated={false}
          component={NotFoundScreen}
        />
      </MemoryRouter>
    );

    expect(render).toMatchSnapshot();
  });

  it("should redirect for authenticated access", () => {
    const render = renderToString(
      <MemoryRouter initialEntries={["/private"]}>
        <RoutePrivate
          key={"test"}
          path={"test"}
          to={"test"}
          isAuthenticated={true}
          component={NotFoundScreen}
        />
      </MemoryRouter>
    );

    expect(render).toMatchSnapshot();
  });
});
