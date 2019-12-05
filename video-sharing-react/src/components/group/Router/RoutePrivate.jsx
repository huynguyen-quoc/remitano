import { Redirect, Route } from "react-router-dom";

import PropTypes from "prop-types";
import React from "react";

export const RoutePrivate = ({
  component: Component,
  isAuthenticated,
  to,
  path,
  ...rest
}) => (
  <Route
    {...rest}
    render={props =>
      isAuthenticated ? (
        <Component {...props} />
      ) : (
        <Redirect
          key={path}
          to={{
            pathname: to
          }}
        />
      )
    }
  />
);

RoutePrivate.propTypes = {
  component: PropTypes.oneOfType([PropTypes.func, PropTypes.object]).isRequired,
  isAuthenticated: PropTypes.bool.isRequired,
  location: PropTypes.object,
  to: PropTypes.string
};

RoutePrivate.defaultProps = {
  to: "/"
};

export default RoutePrivate;
