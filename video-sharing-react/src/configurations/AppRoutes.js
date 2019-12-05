import { lazy } from "react";

/* istanbul ignore next */
export const AppRoutes = [
  {
    path: "/404",
    component: lazy(
      /* istanbul ignore next */ () => import("containers/screens/NotFound")
    )
  }
];
