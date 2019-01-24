package rw.transax.hahiye.callback;

import rw.transax.hahiye.model.FeedsModel;

public interface ClosingCreateFeedView {
    /*
     * Callback function that will be called when some action happens on
     * create feed custom view
     */

    void closeFeedView(int position);

    void createFeed(FeedsModel feedsModel, int position);
}
