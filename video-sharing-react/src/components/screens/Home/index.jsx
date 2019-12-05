import React, { PureComponent } from "react";

import InfiniteScroll from "react-infinite-scroller";
import PropTypes from "prop-types";
import VideoPlayer from "./components/VideoPlayer";

class HomeScreen extends PureComponent {
  static propTypes = {
    fetch: PropTypes.func.isRequired,
    videos: PropTypes.object.isRequired
  };

  constructor(props) {
    super(props);
    this.handleLoadMore = this.handleLoadMore.bind(this);
  }

  componentDidMount() {
    const { fetch } = this.props;
    const param = { page_index: 0 };
    fetch(param);
  }

  handleLoadMore(page) {
    const { fetch } = this.props;
    const param = { page_index: page };
    fetch(param);
  }
  render() {
    const { videos } = this.props;
    const { data, page } = videos;
    return (
      <div className="container">
        <InfiniteScroll
          pageStart={0}
          loadMore={this.handleLoadMore}
          hasMore={page.has_next}
          loader={
            <div className="loader text-center" key={0}>
              Loading ...
            </div>
          }
        >
          {data.map(video => {
            const { id, url, title, description, user } = video;
            const { email } = user;

            return (
              <div className="card mb-2" key={id}>
                <div className="row no-gutters" style={{ height: "15rem" }}>
                  <div className="col-md-5">
                    <VideoPlayer url={url} />
                  </div>
                  <div className="col-md-7">
                    <div className="card-body">
                      <h5 className="card-title">{title}</h5>
                      <p className="card-text">
                        <small className="text-muted">Shared by: {email}</small>
                      </p>
                      <p className="card-text">{description}</p>
                    </div>
                  </div>
                </div>
              </div>
            );
          })}
        </InfiniteScroll>
      </div>
    );
  }
}

export default HomeScreen;
