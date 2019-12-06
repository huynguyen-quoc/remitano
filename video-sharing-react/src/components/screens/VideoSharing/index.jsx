import React, { PureComponent } from "react";

import PropTypes from "prop-types";
import VideoSharingForm from "components/basic/Form/VideoSharing";

class VideoSharingScreen extends PureComponent {
  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.state = {
      videoUrl: ""
    };
  }

  static propTypes = {
    shareVideo: PropTypes.func.isRequired,
    user: PropTypes.object.isRequired
  };

  handleSubmit(e) {
    e.preventDefault();
    const { shareVideo, user } = this.props;
    const { info } = user;
    const { token } = info;
    const { videoUrl } = this.state;
    const param = { videoUrl: videoUrl, userToken: token };
    shareVideo(param);
  }

  handleChange(e) {
    e.preventDefault();
    this.setState({ [e.target.name]: e.target.value });
  }

  render() {
    const { videoUrl } = this.state;
    return (
      <div className="card mb-2">
        <div className="row no-gutters" style={{ height: "15rem" }}>
          <div className="col">
            <div className="card-body">
              <h5 className="card-title">Share a Youtube movie</h5>
              <VideoSharingForm
                onChange={this.handleChange}
                onSubmit={this.handleSubmit}
                videoUrl={videoUrl}
              />
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default VideoSharingScreen;
