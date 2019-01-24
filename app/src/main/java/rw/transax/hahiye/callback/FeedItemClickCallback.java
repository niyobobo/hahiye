package rw.transax.hahiye.callback;

import rw.transax.hahiye.model.FeedsModel;

public interface FeedItemClickCallback {
    void commentOnFeed(FeedsModel feedItem);

    void likeFeed(FeedsModel feedItem);
}
