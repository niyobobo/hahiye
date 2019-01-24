package rw.transax.hahiye.ui.viewHolder;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import butterknife.BindView;
import rw.transax.hahiye.R;
import rw.transax.hahiye.callback.ClosingCreateFeedView;
import rw.transax.hahiye.model.FeedsModel;

public class CreateFeedViewHolder extends BaseViewHolder<FeedsModel> {

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

    private ClosingCreateFeedView closeView;

    public CreateFeedViewHolder(@NonNull View itemView, ClosingCreateFeedView closeView) {
        super(itemView);
        this.closeView = closeView;
    }

    @Override
    public void bind(FeedsModel item) {
        closePost.setOnClickListener(v -> closeView.closeFeedView(getAdapterPosition()));
        sendPost.setOnClickListener(v -> {
            FeedsModel feedsModel = new FeedsModel();
            feedsModel.setType(0);
            feedsModel.setDate("");
            feedsModel.setDescription(postContent.getText().toString());
            closeView.createFeed(feedsModel, getAdapterPosition());
        });
    }
}
