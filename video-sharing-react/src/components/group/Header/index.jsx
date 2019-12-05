import React, { PureComponent } from "react";

import { Link } from "react-router-dom";
import LoginForm from "components/basic/Form/Login";
import LogoutForm from "components/basic/Form/Logout";
import PropTypes from "prop-types";
import classnames from "classnames";

class Header extends PureComponent {
  static propTypes = {
    authenticate: PropTypes.func.isRequired,
    logout: PropTypes.func.isRequired,
    user: PropTypes.object.isRequired
  };

  constructor(props) {
    super(props);
    this.handleToggle = this.handleToggle.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.handleLogout = this.handleLogout.bind(this);
    this.state = {
      email: "",
      password: ""
    };
  }

  handleToggle(e) {
    e.preventDefault();
    this.setState(prevState => ({
      toggle: !prevState.toggle
    }));
  }

  handleChange(e) {
    e.preventDefault();
    this.setState({ [e.target.name]: e.target.value });
  }

  handleLogout(e) {
    e.preventDefault();
    const { logout } = this.props;
    logout();
  }

  handleSubmit(e) {
    e.preventDefault();
    const { authenticate } = this.props;
    const { email, password } = this.state;

    authenticate({ email: email, password: password });
  }

  render() {
    const { toggle, email, password } = this.state;
    const { user } = this.props;
    const { isAuthenticated } = user;
    return (
      <nav className="navbar navbar-expand-sm navbar-dark bg-primary mb-4">
        <div className="container">
          <Link className="navbar-brand" to="/">
            Funny Movies
          </Link>
          <button
            className="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#mobile-nav"
            onClick={this.handleToggle}
          >
            <span className="navbar-toggler-icon" />
          </button>

          <div
            className={classnames("collapse navbar-collapse", {
              show: toggle
            })}
            id="mobile-nav"
          >
            <ul className="navbar-nav ml-auto">
              <li className="nav-item">
                {!isAuthenticated ? (
                  <LoginForm
                    email={email}
                    password={password}
                    onSubmit={this.handleSubmit}
                    onChange={this.handleChange}
                  />
                ) : (
                  <LogoutForm user={user} onLogout={this.handleLogout} />
                )}
              </li>
            </ul>
          </div>
        </div>
      </nav>
    );
  }
}
export default Header;
