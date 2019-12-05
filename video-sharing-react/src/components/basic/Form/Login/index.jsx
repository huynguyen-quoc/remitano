import PropTypes from "prop-types";
import React from "react";
import classnames from "classnames";

function LoginForm(props) {
  const { errors, email, password, onChange, onSubmit } = props;
  return (
    <form onSubmit={onSubmit}>
      <div className="form-row align-items-center">
        <div className="col-auto">
          <input
            type="text"
            className={classnames("form-control form-control-sm", {
              "is-invalid": errors.email
            })}
            placeholder="Email Address"
            name="email"
            value={email}
            onChange={onChange}
          />
        </div>
        <div className="col-auto">
          <input
            type="password"
            className={classnames("form-control form-control-sm", {
              "is-invalid": errors.password
            })}
            placeholder="Password"
            name="password"
            value={password}
            onChange={onChange}
          />
        </div>
        <div className="col-auto">
          <button type="submit" className="btn btn-success">
            Login / Register
          </button>
        </div>
      </div>
    </form>
  );
}
LoginForm.defaultProps = {
  errors: {},
  email: "",
  password: ""
};

LoginForm.propTypes = {
  errors: PropTypes.object,
  email: PropTypes.string.isRequired,
  password: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
  onSubmit: PropTypes.func
};

export default LoginForm;
