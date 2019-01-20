package rw.transax.hahiye.callback;

import rw.transax.hahiye.model.FeedsModel;

public interface FeedComment {
    void makeComment(FeedsModel feedItem);

    void makeLike(FeedComment feedItem);
}
