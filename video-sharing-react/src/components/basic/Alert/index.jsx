import PropTypes from "prop-types";
import React from "react";

function Alert(props) {
  const { message, type, onClick } = props;
  return (
    <div className={`alert alert-${type}`} role="alert">
      {message}
      <button
        type="button"
        className="close"
        data-dismiss="alert"
        aria-label="Close"
        onClick={onClick}
      >
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
  );
}
Alert.defaultProps = {
  type: "info",
  show: false
};
Alert.propTypes = {
  message: PropTypes.string,
  type: PropTypes.string,
  onClick: PropTypes.func
};

export default Alert;
