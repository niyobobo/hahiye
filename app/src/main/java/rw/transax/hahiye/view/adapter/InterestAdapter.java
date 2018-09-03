package rw.transax.hahiye.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import rw.transax.hahiye.R;
import rw.transax.hahiye.model.InterestModel;

public class InterestAdapter extends ListAdapter<InterestModel, InterestAdapter.ViewHolder> {

    public interface InterestSelected {
        void onInterestSelected(InterestModel interest);
    }

    private Context context;
    private InterestSelected interestSelected;

    /**
     * @param context          Context of the parent activity
     * @param interestSelected button selected callback that will notify activity through this interface
     *                         whether interest selected or deselected.
     */

    public InterestAdapter(Context context, InterestSelected interestSelected) {
        super(DIFF_CALLBACK);
        this.context = context;
        this.interestSelected = interestSelected;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_interest_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    @Override
    public void submitList(@Nullable List<InterestModel> list) {
        super.submitList(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView icon;
        private Button select;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.interest_name);
            icon = itemView.findViewById(R.id.interest_icon);
            select = itemView.findViewById(R.id.btn_select);
        }

        void bindTo(InterestModel interest) {
            name.setText(interest.getName());

            Glide.with(context)
                    .load(interest.getIcon())
                    .into(icon);

            select.setOnClickListener(view -> {
                if (interestSelected != null)
                    interestSelected.onInterestSelected(interest);
            });
        }
    }


    /**
     * DiffUtil object that calculate difference between data passed to adapter using submitList()
     * method and adapter change accordingly
     */

    private static final DiffUtil.ItemCallback<InterestModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<InterestModel>() {

        @Override
        public boolean areItemsTheSame(@NonNull InterestModel interestModel, @NonNull InterestModel t1) {
            return interestModel.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull InterestModel interestModel, @NonNull InterestModel t1) {
            return interestModel.equals(t1);
        }

        @Nullable
        @Override
        public Object getChangePayload(@NonNull InterestModel oldItem, @NonNull InterestModel newItem) {
            return super.getChangePayload(oldItem, newItem);
        }
    };
}
