import PropTypes from "prop-types";
import React from "react";
import classnames from "classnames";

function VideoSharingForm(props) {
  const { errors, videoUrl, onChange, onSubmit } = props;
  return (
    <form onSubmit={onSubmit}>
      <div className="align-items-center">
        <div className="form-group">
          <label htmlFor="url" className="col-form-label">
            Youtube URL
          </label>
          <input
            type="text"
            className={classnames("form-control", {
              "is-invalid": errors.url
            })}
            required
            placeholder="https://www.youtube.com/watch?v=video_id"
            name="videoUrl"
            value={videoUrl}
            onChange={onChange}
          />
        </div>
        <button type="submit" className="btn btn-success float-right">
          Share
        </button>
      </div>
    </form>
  );
}
VideoSharingForm.defaultProps = {
  errors: {},
  url: ""
};

VideoSharingForm.propTypes = {
  errors: PropTypes.object,
  url: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
  onSubmit: PropTypes.func
};

export default VideoSharingForm;
