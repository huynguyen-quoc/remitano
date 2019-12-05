import { checkPermissions } from 'utils/helpers';

const AccessControl = ({
  user,
  userPermissions,
  allowedPermissions,
  children,
  renderNoAccess,
  accessCheck,
  extraAccessData,
}) => {
  let permitted;
  // when an accessCheck function is provided, ensure that passes as well as the permissions
  if (accessCheck) {
    permitted =
      accessCheck(extraAccessData, user) && checkPermissions(userPermissions, allowedPermissions);
  } else {
    // otherwise only check permissions
    permitted = checkPermissions(userPermissions, allowedPermissions);
  }

  if (permitted) {
    return children;
  }
  return renderNoAccess();
};

AccessControl.defaultProps = {
  allowedPermissions: [],
  userPermissions: [],
  renderNoAccess: () => null,
};

export default AccessControl;
