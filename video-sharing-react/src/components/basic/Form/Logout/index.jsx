import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import React from "react";

function LogoutForm(props) {
  const { user, onLogout, onSharing } = props;
  const { info } = user;
  const { email } = info;
  return (
    <div className="form-row align-items-center">
      <div className="collapse navbar-collapse" id="mobile-nav">
        <ul className="navbar-nav ml-auto">
          <li className="nav-item mr-2">
            <Link className="nav-link text-white" to="/dashboard">
              Wellcome <b>{email}</b>
            </Link>
          </li>
          <li className="nav-item mr-2">
            <button
              type="button"
              className="btn btn-success"
              onClick={onSharing}
            >
              Share a movie
            </button>
          </li>
          <li className="nav-item">
            <button type="button" className="btn btn-danger" onClick={onLogout}>
              Logout
            </button>
          </li>
        </ul>
      </div>
    </div>
  );
}
LogoutForm.defaultProps = {
  user: {}
};

LogoutForm.propTypes = {
  user: PropTypes.object,
  onLogout: PropTypes.func,
  onSharing: PropTypes.func
};

export default LogoutForm;
