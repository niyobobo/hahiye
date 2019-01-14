package rw.transax.hahiye.utils;

import android.os.Handler;
import android.view.MotionEvent;
import android.widget.ProgressBar;

public class ControlProgress {

    private int progressStatus = 0;
    private Boolean hold = true;
    private Handler handler = new Handler();

    public boolean ControlProgress(ProgressBar progressBar, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                new Thread(() -> {
                    while (progressStatus < 100 && hold) {
                        progressStatus++;
                        /*
                         * Update progressBar value
                         */
                        handler.post(() -> progressBar.setProgress(progressStatus));
                        try {
                            // add delay of 20 milliseconds.
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                return true;
            case MotionEvent.ACTION_UP:
                hold = false;
                return false;
        }
        return false;
    }
}
