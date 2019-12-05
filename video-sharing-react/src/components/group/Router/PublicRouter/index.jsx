import React from "react";
import PropTypes from "prop-types";
import { ConnectedRouter } from "connected-react-router";
import { Switch } from "react-router";
import { AppConfig } from "configurations";
import RoutePublic from "./RoutePublic";
import RoutePrivate from "./RoutePrivate";

function PublicRouter(props) {
  const {
    isAuthenticated,
    DashBoardPage,
    NotFoundPage,
    UnauthorizedPage
  } = props;
  return (
    <ConnectedRouter history={routerHistory}>
      <Switch>
        <RoutePrivate
          path={AppConfig.routePrefix}
          isAuthenticated={isAuthenticated}
          component={DashBoardPage}
        />
        <RoutePublic
          isAuthenticated={isAuthenticated}
          component={NotFoundPage}
        />
      </Switch>
    </ConnectedRouter>
  );
}

PublicRouter.propTypes = {
  DashBoardPage: PropTypes.any.isRequired,
  isAuthenticated: PropTypes.bool.isRequired,
  UnauthorizedPage: PropTypes.any.isRequired,
  NotFoundPage: PropTypes.any.isRequired
};

export default PublicRouter;
