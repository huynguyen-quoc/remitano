import { MemoryRouter } from "react-router-dom";
import NotFoundScreen from "containers/screens/NotFound";
import React from "react";
import RoutePublic from "components/group/Router/RoutePublic";
import { renderToString } from "react-dom/server";

describe("Route Public", () => {
  it("should redirect for unauthenticated access", () => {
    const render = renderToString(
      <MemoryRouter initialEntries={["/private"]}>
        <RoutePublic isAuthenticated={false} component={NotFoundScreen} />
      </MemoryRouter>
    );

    expect(render).toMatchSnapshot();
  });

  it("should redirect for authenticated access", () => {
    const render = renderToString(
      <MemoryRouter initialEntries={["/private"]}>
        <RoutePublic isAuthenticated={true} component={NotFoundScreen} />
      </MemoryRouter>
    );

    expect(render).toMatchSnapshot();
  });
});
