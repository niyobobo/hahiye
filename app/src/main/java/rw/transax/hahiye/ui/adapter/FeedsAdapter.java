package rw.transax.hahiye.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import rw.transax.hahiye.R;
import rw.transax.hahiye.callback.FeedComment;
import rw.transax.hahiye.model.FeedsModel;
import rw.transax.hahiye.ui.viewHolder.BaseViewHolder;
import rw.transax.hahiye.ui.viewHolder.CreatePostTypeViewHolder;
import rw.transax.hahiye.ui.viewHolder.ImageTypeViewHolder;
import rw.transax.hahiye.ui.viewHolder.TextTypeViewHolder;

public class FeedsAdapter extends RecyclerView.Adapter<BaseViewHolder<FeedsModel>> {

    private static final int TEXT_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private static final int CREATE_POST_TYPE = 2;

    private List<FeedsModel> dataSet;
    private FeedComment feedComment;
    private Context mContext;

    public FeedsAdapter(Context context, FeedComment feedComment) {
        mContext = context;
        this.feedComment = feedComment;
    }

    @NonNull
    @Override
    public BaseViewHolder<FeedsModel> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {

            case TEXT_TYPE: {
                view = layoutInflater.inflate(R.layout.item_feed_text, parent, false);
                return new TextTypeViewHolder(view, dataSet, mContext, feedComment);
            }
            case IMAGE_TYPE: {
                view = layoutInflater.inflate(R.layout.item_feed_text, parent, false);
                return new ImageTypeViewHolder(view);
            }
            case CREATE_POST_TYPE: {
                view = layoutInflater.inflate(R.layout.item_create_post, parent, false);
                return new CreatePostTypeViewHolder(view);
            }
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<FeedsModel> holder, int position) {
        FeedsModel model = dataSet.get(position);
        holder.bind(model);
    }

    public void add(List<FeedsModel> feedData) {
        if (dataSet == null) dataSet = new ArrayList<>();
        dataSet.clear();
        dataSet.addAll(feedData);
        notifyDataSetChanged();
    }

    public void createFeed(FeedsModel model) {
        dataSet.add(3, model);
        notifyItemInserted(3);
    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        FeedsModel data = dataSet.get(position);
        switch (data.getType()) {
            case 0:
                return TEXT_TYPE;
            case 1:
                return IMAGE_TYPE;
            case 2:
                return CREATE_POST_TYPE;
            default:
                throw new IllegalArgumentException("Invalid position " + position);
        }
    }
}