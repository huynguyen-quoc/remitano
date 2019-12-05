// @flow
/**
 * Helper functions
 * @module Helpers
 */

export function checkPermissions(
  userPermissions: Array<String>,
  allowedPermissions: Array<String>,
): boolean {
  if (allowedPermissions && allowedPermissions.length > 0) {
    return userPermissions.some(permission => allowedPermissions.includes(permission));
  }
  return true;
}