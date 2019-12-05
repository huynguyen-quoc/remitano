import { lazy } from "react";
/* istanbul ignore next */
const AppRoutes = [
  {
    path: "/video-sharing",
    component: lazy(
      /* istanbul ignore next */ () => import("containers/screens/Home")
    )
  }
];

export default AppRoutes;
