import { persistor, store } from "./store";

import ConnectedHomeScreen from "containers/screens/Home";
import ConnectedMainScreen from "containers/screens/Main";
import NotFoundScreen from "containers/screens/NotFound";
import { PersistGate } from "redux-persist/lib/integration/react";
import { Provider } from "react-redux";
import React from "react";

function App() {
  return (
    <Provider store={store}>
      <PersistGate persistor={persistor}>
        <ConnectedMainScreen
          notFoundComponent={NotFoundScreen}
          homeComponent={ConnectedHomeScreen}
        />
      </PersistGate>
    </Provider>
  );
}

export default App;
