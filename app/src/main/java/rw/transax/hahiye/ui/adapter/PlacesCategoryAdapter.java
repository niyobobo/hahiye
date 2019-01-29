package rw.transax.hahiye.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import rw.transax.hahiye.R;
import rw.transax.hahiye.model.SingleHorizontal;
import rw.transax.hahiye.ui.viewHolder.PlaceCategoryViewHolder;

import static rw.transax.hahiye.model.SingleHorizontal.HORIZONTAL_ITEM_CALLBACK;

public class PlacesCategoryAdapter extends ListAdapter<SingleHorizontal, PlaceCategoryViewHolder> {

    PlacesCategoryAdapter() {
        super(HORIZONTAL_ITEM_CALLBACK);
    }

    @NonNull
    @Override
    public PlaceCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_place_category, parent, false);
        return new PlaceCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceCategoryViewHolder holder, int position) {

    }

}
