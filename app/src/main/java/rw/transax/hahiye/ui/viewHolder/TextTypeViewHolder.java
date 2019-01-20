package rw.transax.hahiye.ui.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import rw.transax.hahiye.R;
import rw.transax.hahiye.callback.FeedComment;
import rw.transax.hahiye.model.FeedsModel;

public class TextTypeViewHolder extends BaseViewHolder<FeedsModel> {

    private Context context;
    private List<FeedsModel> itemModels;
    private FeedComment feedComment;

    @BindView(R.id.img_feed_posterImg)
    CircleImageView userProfile;

    @BindView(R.id.txt_feed_content)
    TextView feedTextContent;

    @BindView(R.id.txt_feed_createdAt)
    TextView feedCreatedTime;

    @BindView(R.id.btn_feed_comment)
    AppCompatImageView btn_commenting;

    @BindView(R.id.btn_feed_like)
    AppCompatImageView likeFeed;

    @BindView(R.id.feed_hitLevel)
    ProgressBar feedHitLevel;

    public TextTypeViewHolder(@NonNull View itemView, List<FeedsModel> itemModels, Context context, FeedComment feedComment) {
        super(itemView);
        this.itemModels = itemModels;
        this.context = context;
        this.feedComment = feedComment;
    }

    @Override
    public void bind(FeedsModel item) {
        btn_commenting.setOnClickListener(v -> feedComment.makeComment(item));
    }
}
