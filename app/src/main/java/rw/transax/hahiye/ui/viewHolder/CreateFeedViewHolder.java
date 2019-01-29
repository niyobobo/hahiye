package rw.transax.hahiye.ui.viewHolder;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import butterknife.BindView;
import rw.transax.hahiye.R;
import rw.transax.hahiye.callback.CreatingFeedCallback;
import rw.transax.hahiye.model.Feeds;

public class CreateFeedViewHolder extends BaseViewHolder<Feeds> {

    @BindView(R.id.btn_send_post)
    AppCompatImageView sendPost;

    @BindView(R.id.btn_close_post)
    AppCompatImageView closePost;

    @BindView(R.id.edt_post_content)
    EditText postContent;

    @BindView(R.id.img_add_image)
    AppCompatImageView uploadImage;

    @BindView(R.id.img_feature_post)
    AppCompatImageView postFeatureImage;

    @BindView(R.id.img_share_loc)
    AppCompatImageView enableYourLocation;

    private CreatingFeedCallback closeView;

    public CreateFeedViewHolder(@NonNull View itemView, CreatingFeedCallback closeView) {
        super(itemView);
        this.closeView = closeView;
    }

    @Override
    public void bind(Feeds item) {
        closePost.setOnClickListener(v -> closeView.closeFeedView(getAdapterPosition()));
        sendPost.setOnClickListener(v -> {
            Feeds feeds = new Feeds();
            feeds.setType(0);
            feeds.setDate("");
            feeds.setDescription(postContent.getText().toString());
            closeView.createFeed(feeds, getAdapterPosition());
        });
    }
}
