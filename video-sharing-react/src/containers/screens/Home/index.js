import HomeScreen from "components/screens/Home";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";
import { fetch } from "actions";

/* istanbul ignore next */
function mapStateToProps(state) {
  return {
    videos: state.videos
  };
}
/* istanbul ignore next */
function mapDispatchToProps(dispatch) {
  return {
    fetch: bindActionCreators(fetch, dispatch)
  };
}
/* istanbul ignore next */
const ConnectedHomeScreen = connect(
  mapStateToProps,
  mapDispatchToProps
)(HomeScreen);

export default ConnectedHomeScreen;
