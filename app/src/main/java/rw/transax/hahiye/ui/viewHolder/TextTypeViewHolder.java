package rw.transax.hahiye.ui.viewHolder;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import rw.transax.hahiye.R;
import rw.transax.hahiye.callback.FeedItemClickCallback;
import rw.transax.hahiye.model.FeedsModel;

public class TextTypeViewHolder extends BaseViewHolder<FeedsModel> {

    @BindView(R.id.img_feed_posterImg)
    CircleImageView userProfile;

    @BindView(R.id.txt_feed_content)
    TextView feedTextContent;

    @BindView(R.id.txt_feed_createdAt)
    TextView feedCreatedTime;

    @BindView(R.id.btn_feed_comment)
    AppCompatImageView btn_commenting;

    @BindView(R.id.btn_feed_like)
    AppCompatImageView btn_like;

    @BindView(R.id.feed_hitLevel)
    ProgressBar feedHitLevel;

    private int progressStatus = 0;
    private Boolean running = true;
    private FeedItemClickCallback feedItemClickCallback;
    private android.os.Handler handler = new android.os.Handler();

    public TextTypeViewHolder(@NonNull View itemView, FeedItemClickCallback feedItemClickCallback) {
        super(itemView);
        this.feedItemClickCallback = feedItemClickCallback;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void bind(FeedsModel item) {
        btn_commenting.setOnClickListener(v -> feedItemClickCallback.commentOnFeed(item));
        btn_like.setOnTouchListener((v, event) -> likeFeed(event, item));
    }

    private boolean likeFeed(MotionEvent event, FeedsModel item) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            new Thread(() -> {
                while (progressStatus < 100 && running) {
                    progressStatus++;
                    /*
                     * Update progressBar value
                     */
                    handler.post(() -> feedHitLevel.setProgress(progressStatus));
                    try {
                        // add delay of 20 milliseconds.
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            running = false;
            feedItemClickCallback.likeFeed(item);
        }
        return false;
    }
}