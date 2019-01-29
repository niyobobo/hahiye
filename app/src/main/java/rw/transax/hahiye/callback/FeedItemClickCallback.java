package rw.transax.hahiye.callback;

import rw.transax.hahiye.model.Feeds;

public interface FeedItemClickCallback {
    void commentOnFeed(Feeds feedItem);

    void likeFeed(Feeds feedItem);
}
