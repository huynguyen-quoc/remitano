import { lazy } from "react";
/* istanbul ignore next */
const AppRoutes = [
  {
    path: "/video-sharing",
    exact: true,
    component: lazy(
      /* istanbul ignore next */ () => import("containers/screens/VideoSharing")
    )
  }
];

export default AppRoutes;
