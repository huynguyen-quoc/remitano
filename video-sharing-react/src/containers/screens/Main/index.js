import { clearMessage, create, logout } from "actions";

import MainScreen from "components/screens/Main";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";

/* istanbul ignore next */
function mapStateToProps(state) {
  return {
    user: state.user,
    app: state.app
  };
}
/* istanbul ignore next */
function mapDispatchToProps(dispatch) {
  return {
    create: bindActionCreators(create, dispatch),
    logout: bindActionCreators(logout, dispatch),
    clearMessage: bindActionCreators(clearMessage, dispatch)
  };
}
/* istanbul ignore next */
const ConnectedMainScreen = connect(
  mapStateToProps,
  mapDispatchToProps
)(MainScreen);

export default ConnectedMainScreen;
