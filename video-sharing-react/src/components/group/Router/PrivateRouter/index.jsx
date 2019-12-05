import React, { Suspense } from "react";
import { ConnectedRouter } from "connected-react-router";
import { Switch } from "react-router";
import { Route, Redirect } from "react-router-dom";
import PropTypes from "prop-types";
import routerHistory from "modules/routerHistory";
import { checkPermissions } from "utils/helpers";
import { AppConfig } from "configurations";

export const LoadingMessage = () => "Loading...";

export function PrivateRouter(props) {
  const { userPermissions, routes, NotFoundPage } = props;
  return (
    <ConnectedRouter history={routerHistory}>
      <Suspense fallback={<LoadingMessage />}>
        <Switch>
          {routes.map(route => {
            const { path, exact, permission, ...rest } = route;
            const { values, type } = permission || { values: [], type: "some" };
            const permited = checkPermissions(
              userPermissions,
              values || [],
              type || "some"
            );
            return permited ? (
              <Route
                exact={exact}
                key={path}
                path={`${AppConfig.routePrefix}${path}`}
                {...rest}
              />
            ) : (
              <Redirect
                key={path}
                path={`${AppConfig.routePrefix}${path}`}
                {...rest}
                to={{
                  pathname: `${AppConfig.routePrefix}/404`
                }}
              />
            );
          })}
          <Route component={NotFoundPage} />
        </Switch>
      </Suspense>
    </ConnectedRouter>
  );
}

PrivateRouter.propTypes = {
  NotFoundPage: PropTypes.any.isRequired,
  routes: PropTypes.array.isRequired,
  userPermissions: PropTypes.array.isRequired
};

export default PrivateRouter;
