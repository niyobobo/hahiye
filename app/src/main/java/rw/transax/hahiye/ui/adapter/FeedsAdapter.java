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
import rw.transax.hahiye.callback.ClosingCreateFeedView;
import rw.transax.hahiye.callback.FeedItemClickCallback;
import rw.transax.hahiye.model.FeedsModel;
import rw.transax.hahiye.ui.viewHolder.BaseViewHolder;
import rw.transax.hahiye.ui.viewHolder.CreateFeedViewHolder;
import rw.transax.hahiye.ui.viewHolder.ImageTypeViewHolder;
import rw.transax.hahiye.ui.viewHolder.TextTypeViewHolder;

public class FeedsAdapter extends RecyclerView.Adapter<BaseViewHolder<FeedsModel>> {

    private static final int TEXT_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private static final int CREATE_POST_TYPE = 2;

    private List<FeedsModel> dataSet;
    private FeedItemClickCallback clickCallback;
    private ClosingCreateFeedView createFeedView;
    private Context mContext;

    public FeedsAdapter(Context context, FeedItemClickCallback clickCallback, ClosingCreateFeedView feedView) {
        mContext = context;
        this.clickCallback = clickCallback;
        this.createFeedView = feedView;
    }

    @NonNull
    @Override
    public BaseViewHolder<FeedsModel> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {

            case TEXT_TYPE: {
                view = layoutInflater.inflate(R.layout.item_feed_text, parent, false);
                return new TextTypeViewHolder(view, clickCallback);
            }
            case IMAGE_TYPE: {
                view = layoutInflater.inflate(R.layout.item_feed_text, parent, false);
                return new ImageTypeViewHolder(view);
            }
            case CREATE_POST_TYPE: {
                view = layoutInflater.inflate(R.layout.item_create_post, parent, false);
                return new CreateFeedViewHolder(view, createFeedView);
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

    /**
     * Adding CreatePost View to the recycleview Adapter
     * Don't forget to check whether the view is exist before adding it to the view
     * holder. Only single view needed.
     *
     * @param model make sure type of this view equals to CREATE_POST_TYPE = 2;
     */

    public void createFeed(FeedsModel model) {
        if (getItemViewType(2) != CREATE_POST_TYPE) {
            dataSet.add(2, model);
            notifyItemInserted(2);
        }
    }

    public void removeCreateFeedView(int position, View v) {
        dataSet.remove(position);
        notifyItemRemoved(position);
        v.setVisibility(v.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
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