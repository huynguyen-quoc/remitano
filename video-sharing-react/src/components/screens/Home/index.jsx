import React, { PureComponent } from "react";

export class HomePage extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className="error-content">
          <h1>404</h1>
          <h3>Looks like you got lost</h3>
          <p>The page youre looking for doesnt exist or has been moved</p>
        </div>
        <div className="error-art-work">
          <img alt="#" src={Image} />
        </div>
      </React.Fragment>
    );
  }
}

export default HomePage;
