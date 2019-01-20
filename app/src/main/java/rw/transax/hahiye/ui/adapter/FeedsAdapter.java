package rw.transax.hahiye.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import rw.transax.hahiye.R;

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.FeedsViewHolder> {
    private Context mContext;

    public FeedsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FeedsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_feed_text, parent, false);
        return new FeedsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedsViewHolder holder, int position) {
        holder.bindView();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class FeedsViewHolder extends RecyclerView.ViewHolder {
        private int progressStatus = 0;
        private Boolean running = true;
        private Handler handler = new Handler();
        private ProgressBar feed_hitLevel;
        private ImageView btn_like;

        FeedsViewHolder(@NonNull View itemView) {
            super(itemView);
            feed_hitLevel = itemView.findViewById(R.id.feed_hitLevel);
            btn_like = itemView.findViewById(R.id.btn_feed_like);
        }

        @SuppressLint("ClickableViewAccessibility")
        void bindView() {
            btn_like.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    new Thread(() -> {
                        while (progressStatus < 100 && running) {
                            progressStatus++;
                            /*
                             * Update progressBar value
                             */
                            handler.post(() -> feed_hitLevel.setProgress(progressStatus));
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
                }
                return false;
            });
        }
    }
}