import React, { PureComponent } from "react";

import { BrowserRouter } from "react-router-dom";
import Header from "components/group/Header";
import PropTypes from "prop-types";
import Router from "containers/group/Router";
import { AppRoutes as routes } from "configurations";

class MainScreen extends PureComponent {
  static propTypes = {
    user: PropTypes.object.isRequired,
    create: PropTypes.func.isRequired,
    logout: PropTypes.func.isRequired,
    notFoundComponent: PropTypes.any.isRequired
  };
  render() {
    const { user, notFoundComponent, create, logout } = this.props;
    return (
      <BrowserRouter>
        <Header create={create} user={user} logout={logout} />
        <Router
          routes={routes}
          notFoundComponent={notFoundComponent}
          isAuthenticated={user.isAuthenticated}
        />
      </BrowserRouter>
    );
  }
}

export default MainScreen;
