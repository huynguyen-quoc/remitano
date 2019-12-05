import PropTypes from "prop-types";
import React from "react";
import ReactPlayer from "react-player";

function VideoPlayer(props) {
  const { url } = props;
  return <ReactPlayer url={url} width="100%" height="100%" />;
}

VideoPlayer.propTypes = {
  url: PropTypes.string.isRequired
};

export default VideoPlayer;
