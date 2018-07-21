package rw.transax.hahiye.callback;

public interface ResultCallback {

    void onSuccess(String message);

    void onError(String error);
}
