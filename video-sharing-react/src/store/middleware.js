import createSagaMiddleware from "redux-saga";
import { createLogger } from "redux-logger";

export const sagaMiddleware = createSagaMiddleware();

const middleware = [sagaMiddleware];

/* istanbul ignore next */
if (process.env.NODE_ENV === "development") {
  const logger = createLogger({
    collapsed: true,
    diff: true,
    duration: true
  });

  middleware.push(logger);
}
export default middleware;
