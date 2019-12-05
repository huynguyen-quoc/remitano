import { login, logout } from "actions";

import MainScreen from "components/screens/Main";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";

/* istanbul ignore next */
function mapStateToProps(state) {
  return {
    user: state.user
  };
}
/* istanbul ignore next */
function mapDispatchToProps(dispatch) {
  return {
    authenticate: bindActionCreators(login, dispatch),
    logout: bindActionCreators(logout, dispatch)
  };
}
/* istanbul ignore next */
const ConnectedMainScreen = connect(
  mapStateToProps,
  mapDispatchToProps
)(MainScreen);

export default ConnectedMainScreen;
