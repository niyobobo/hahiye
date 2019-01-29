package rw.transax.hahiye.callback;

import rw.transax.hahiye.model.Interest;

/**
 * Call back to be implemented by Interest activity so that
 * it will be called when a single interest clicked.
 */

public interface InterestSelected {
    void onInterestSelected(Interest interest);
}
