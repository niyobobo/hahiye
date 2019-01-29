package rw.transax.hahiye.callback;

import rw.transax.hahiye.model.Feeds;

public interface CreatingFeedCallback {
    /*
     * Callback function that will be called when some action happens on
     * create feed custom view
     */

    void closeFeedView(int position);

    void createFeed(Feeds feeds, int position);
}
