import { AppConfig } from "configurations";
import PropTypes from "prop-types";
import React from "react";
import { Route } from "react-router-dom";
import RoutePrivate from "./RoutePrivate";
import RoutePublic from "./RoutePublic";
import { Switch } from "react-router";

function Router(props) {
  const { isAuthenticated, notFoundComponent, homeComponent, routes } = props;
  return (
    <Switch>
      {routes.map(route => {
        const { path, exact, component, ...rest } = route;

        return (
          <RoutePrivate
            key={`${AppConfig.routePrefix}${path}`}
            path={`${AppConfig.routePrefix}${path}`}
            to={`${AppConfig.routePrefix}/404`}
            isAuthenticated={isAuthenticated}
            component={component}
            {...rest}
          />
        );
      })}
      <Route path="/" component={homeComponent}></Route>
      <RoutePublic
        isAuthenticated={isAuthenticated}
        component={notFoundComponent}
      />
    </Switch>
  );
}

Router.propTypes = {
  isAuthenticated: PropTypes.bool.isRequired,
  routes: PropTypes.array.isRequired,
  notFoundComponent: PropTypes.any.isRequired,
  homeComponent: PropTypes.any.isRequired
};

export default Router;
