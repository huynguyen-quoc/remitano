import React, { PureComponent } from "react";

import NotFoundWrapper from "./components/NotFoundWrapper";

export class NotFoundScreen extends PureComponent {
  render() {
    return (
      <NotFoundWrapper className="404-error-page">
        <div className="error-content">
          <h1>404</h1>
          <h3>Looks like you got lost</h3>
          <p>The page youre looking for doesnt exist or has been moved</p>
        </div>
      </NotFoundWrapper>
    );
  }
}

export default NotFoundScreen;
