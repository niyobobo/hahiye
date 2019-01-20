package rw.transax.hahiye.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import rw.transax.hahiye.R;
import rw.transax.hahiye.callback.FeedComment;
import rw.transax.hahiye.model.FeedsModel;
import rw.transax.hahiye.ui.adapter.FeedsAdapter;
import rw.transax.hahiye.utils.CreatePostImageView;
import rw.transax.hahiye.utils.DividerItemDecoration;

public class FeedsFragment extends Fragment implements FeedComment {

    private Context mContext;
    private FeedsAdapter feedsAdapter;
    private List<FeedsModel> sample;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        sample = new ArrayList<>();
        feedsAdapter = new FeedsAdapter(mContext, this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feeds, container, false);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.feedsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext));
        recyclerView.setAdapter(feedsAdapter);
        AppCompatImageView mImageView = view.findViewById(R.id.img_create_post);
        mImageView.setOnTouchListener(new CreatePostImageView());

        feedsAdapter.add(getData());
    }


    private List<FeedsModel> getData() {
        FeedsModel feedsModel = new FeedsModel();
        feedsModel.setType(0);
        feedsModel.setDescription("I won't post the reversal animation so this article doesn't get enormous, but I am sure you can figure it out.");
        feedsModel.setDate("Jan 5");
        for (int i = 0; i < 20; i++) {
            sample.add(feedsModel);
        }
        return sample;
    }

    @Override
    public void makeComment(FeedsModel feedItem) {
        FeedsModel newFeed = new FeedsModel();
        newFeed.setType(2);
        feedsAdapter.createFeed(newFeed);
    }

    @Override
    public void makeLike(FeedComment feedItem) {

    }
}