package rw.transax.hahiye.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import rw.transax.hahiye.R;
import rw.transax.hahiye.model.SingleHorizontal;
import rw.transax.hahiye.model.SingleVertical;

import static rw.transax.hahiye.ui.fragment.HomeFragment.getHorizontalData;
import static rw.transax.hahiye.ui.fragment.HomeFragment.getVerticalData;

public class PlacesAdapter extends ListAdapter<Object, RecyclerView.ViewHolder> {

    private static final int VERTICAL_TYPE = 0;
    private static final int HORIZONTAL_TYPE = 1;

    private Context mContext;

    private static final DiffUtil.ItemCallback<Object> PLACE_MODEL_ITEM_CALLBACK =
            new DiffUtil.ItemCallback<Object>() {
                @Override
                public boolean areItemsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
                    return oldItem == newItem;
                }

                @Override
                public boolean areContentsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
                    return oldItem == newItem;
                }
            };

    public PlacesAdapter(Context mContext) {
        super(PLACE_MODEL_ITEM_CALLBACK);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_recycleview, parent, false);
        return new CustomViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VERTICAL_TYPE)
            verticalView((CustomViewHolder) holder);
        else if (holder.getItemViewType() == HORIZONTAL_TYPE)
            horizontalView((CustomViewHolder) holder);
        else
            throw new IllegalArgumentException("Invalid type");
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof SingleVertical)
            return VERTICAL_TYPE;
        else if (getItem(position) instanceof SingleHorizontal)
            return HORIZONTAL_TYPE;
        else
            throw new IllegalArgumentException("Invalid type");
    }

    private void verticalView(CustomViewHolder holder) {
        PlaceItemAdapter adapter1 = new PlaceItemAdapter();
        adapter1.submitList(getVerticalData());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        holder.recyclerView.setAdapter(adapter1);
    }

    private void horizontalView(CustomViewHolder holder) {
        PlacesCategoryAdapter adapter = new PlacesCategoryAdapter();
        adapter.submitList(getHorizontalData());
        holder.recyclerView.setLayoutManager(
                new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(adapter);
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.inner_recyclerView)
        RecyclerView recyclerView;

        CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}