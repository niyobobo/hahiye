package rw.transax.hahiye.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import rw.transax.hahiye.R;
import rw.transax.hahiye.callback.InterestSelected;
import rw.transax.hahiye.model.Interest;

public class InterestAdapter extends ListAdapter<Interest, InterestAdapter.ViewHolder> {

    private Context context;
    private InterestSelected interestSelected;

    /**
     * @param context          Context of the parent activity
     * @param interestSelected button selected callback that will notify activity through interface
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
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View notSelected = inflater.inflate(R.layout.item_interest_layout, parent, false);
        return new ViewHolder(notSelected);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private RoundedImageView icon;
        private ImageView selected;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.interest_name);
            icon = itemView.findViewById(R.id.interest_icon);
            selected = itemView.findViewById(R.id.selected_interest);
        }

        void bindTo(Interest interest) {
            name.setText(interest.getName());
            Glide.with(context)
                    .load(interest.getIcon())
                    .into(icon);

            selected.setVisibility(interest.isFollowed() ? View.VISIBLE : View.GONE);
            icon.setOnClickListener(view -> {
                if (interestSelected != null) interestSelected.onInterestSelected(interest);
            });
        }
    }

    /**
     * DiffUtil object that calculate difference between data passed to adapter using submitList()
     * method and adapter change accordingly
     */

    private static final DiffUtil.ItemCallback<Interest> DIFF_CALLBACK = new DiffUtil.ItemCallback<Interest>() {

        @Override
        public boolean areItemsTheSame(@NonNull Interest oldItem, @NonNull Interest newItem) {
            return oldItem.getId() == newItem.getId()
                    && oldItem.getUid().equals(newItem.getUid());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Interest oldInterest, @NonNull Interest newInterest) {
            return oldInterest.getId() == newInterest.getId()
                    && oldInterest.getUid().equals(newInterest.getUid())
                    && oldInterest.getName().equals(newInterest.getName())
                    && oldInterest.getIcon().equals(newInterest.getIcon())
                    && oldInterest.isFollowed() == (newInterest.isFollowed());
        }

        @Nullable
        @Override
        public Object getChangePayload(@NonNull Interest oldItem, @NonNull Interest newItem) {
            return super.getChangePayload(oldItem, newItem);
        }
    };
}
