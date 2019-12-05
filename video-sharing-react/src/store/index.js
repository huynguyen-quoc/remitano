import { applyMiddleware, combineReducers, compose, createStore } from "redux";
import middleware, { sagaMiddleware } from "./middleware";
import { persistReducer, persistStore } from "redux-persist";

import rootReducer from "reducers";
import rootSaga from "sagas";
import storage from "redux-persist/lib/storage";

const reducer = persistReducer(
  {
    key: "rrsb", // key is required
    storage, // storage is now required
    whitelist: ["app", "user"]
  },
  combineReducers({
    ...rootReducer
  })
);

const composeEnhancer = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

/* istanbul ignore next */
const configStore = (initialState = {}) => {
  const store = createStore(
    reducer,
    initialState,
    composeEnhancer(applyMiddleware(...middleware))
  );

  sagaMiddleware.run(rootSaga);

  if (module.hot) {
    module.hot.accept("reducers", () => {
      store.replaceReducer(require("reducers/index").default);
    });
  }

  return {
    persistor: persistStore(store),
    store
  };
};

const { store, persistor } = configStore();

global.store = store;

export { store, persistor };
