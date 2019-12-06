import VideoSharing from "components/screens/VideoSharing";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";
import { shareVideo } from "actions";

/* istanbul ignore next */
function mapStateToProps(state) {
  return {
    videos: state.videos,
    user: state.user
  };
}
/* istanbul ignore next */
function mapDispatchToProps(dispatch) {
  return {
    shareVideo: bindActionCreators(shareVideo, dispatch)
  };
}
/* istanbul ignore next */
const ConnectedVideoSharing = connect(
  mapStateToProps,
  mapDispatchToProps
)(VideoSharing);

export default ConnectedVideoSharing;
