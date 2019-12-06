import React, { PureComponent } from "react";

import Alert from "components/basic/Alert";
import { BrowserRouter } from "react-router-dom";
import Header from "components/group/Header";
import PropTypes from "prop-types";
import Router from "containers/group/Router";
import { AppRoutes as routes } from "configurations";

class MainScreen extends PureComponent {
  constructor(props) {
    super(props);
    this.handleAlertCloseClick = this.handleAlertCloseClick.bind(this);
    const { clearMessage } = this.props;
    clearMessage();
  }

  static propTypes = {
    user: PropTypes.object.isRequired,
    app: PropTypes.object.isRequired,
    clearMessage: PropTypes.func.isRequired,
    create: PropTypes.func.isRequired,
    logout: PropTypes.func.isRequired,
    notFoundComponent: PropTypes.any.isRequired,
    homeComponent: PropTypes.any.isRequired
  };

  handleAlertCloseClick(e) {
    e.preventDefault();
    const { clearMessage } = this.props;
    clearMessage();
  }

  render() {
    const {
      app,
      user,
      notFoundComponent,
      homeComponent,
      create,
      logout
    } = this.props;
    const { message } = app;
    const { type, content } = message;

    return (
      <BrowserRouter>
        <Header create={create} user={user} logout={logout} />

        <div className="container">
          {type && content && (
            <Alert
              message={content}
              type={type}
              onClick={this.handleAlertCloseClick}
            />
          )}
          <Router
            routes={routes}
            homeComponent={homeComponent}
            notFoundComponent={notFoundComponent}
            isAuthenticated={user.isAuthenticated}
          />
        </div>
      </BrowserRouter>
    );
  }
}

export default MainScreen;
