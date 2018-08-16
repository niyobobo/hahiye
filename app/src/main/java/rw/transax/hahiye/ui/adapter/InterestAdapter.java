package rw.transax.hahiye.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import rw.transax.hahiye.R;
import rw.transax.hahiye.model.InterestModel;

public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.ViewHolder> {

    public interface InterestSelected {
        void onInterestSelected(InterestModel interest);
    }

    private List<InterestModel> data;
    private Context context;
    private InterestSelected InterestSelected;
    private LayoutInflater layoutInflater;

    public InterestAdapter(List<InterestModel> data, Context context, InterestSelected interestSelected) {
        this.data = data;
        this.context = context;
        this.InterestSelected = interestSelected;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_interest_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        if (data != null)
            return data.size();
        else
            return 0;
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

        void bind(InterestModel interest) {
            name.setText(interest.getName());

            Glide.with(context)
                    .setDefaultRequestOptions(new RequestOptions().centerCrop())
                    .load(interest.getIcon())
                    .into(icon);

            select.setOnClickListener(view -> {
                if (InterestSelected != null) {
                    InterestSelected.onInterestSelected(interest);
                }
            });
        }
    }

    public void setData(List<InterestModel> newData) {
        if (data != null) {
            InterestDiffCallback callback = new InterestDiffCallback(data, newData);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);

            data.clear();
            data.addAll(newData);
            diffResult.dispatchUpdatesTo(this);
        }
    }

    class InterestDiffCallback extends DiffUtil.Callback {

        private final List<InterestModel> oldData, newData;

        InterestDiffCallback(List<InterestModel> oldData, List<InterestModel> newData) {
            this.oldData = oldData;
            this.newData = newData;
        }

        @Override
        public int getOldListSize() {
            return oldData.size();
        }

        @Override
        public int getNewListSize() {
            return newData.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldData.get(oldItemPosition).getUid().equals(newData.get(newItemPosition).getUid());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldData.get(oldItemPosition).equals(newData.get(newItemPosition));
        }
    }
}
