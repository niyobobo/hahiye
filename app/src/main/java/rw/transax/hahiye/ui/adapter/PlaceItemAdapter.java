package rw.transax.hahiye.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import rw.transax.hahiye.R;
import rw.transax.hahiye.model.SingleVertical;
import rw.transax.hahiye.ui.viewHolder.PlaceItemViewHolder;

public class PlaceItemAdapter extends ListAdapter<SingleVertical, PlaceItemViewHolder> {

    PlaceItemAdapter() {
        super(VERTICAL_ITEM_CALLBACK);
    }

    @NonNull
    @Override
    public PlaceItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_place, parent, false);
        return new PlaceItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceItemViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private static final DiffUtil.ItemCallback<SingleVertical> VERTICAL_ITEM_CALLBACK =
            new DiffUtil.ItemCallback<SingleVertical>() {
                @Override
                public boolean areItemsTheSame(@NonNull SingleVertical oldItem, @NonNull SingleVertical newItem) {
                    return false;
                }

                @Override
                public boolean areContentsTheSame(@NonNull SingleVertical oldItem, @NonNull SingleVertical newItem) {
                    return false;
                }
            };
}
