package rw.transax.hahiye.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import rw.transax.hahiye.R;
import rw.transax.hahiye.callback.CreatingFeedCallback;
import rw.transax.hahiye.callback.FeedItemClickCallback;
import rw.transax.hahiye.model.Feeds;
import rw.transax.hahiye.ui.adapter.FeedsAdapter;
import rw.transax.hahiye.utils.DividerItemDecoration;

public class FeedsFragment extends Fragment implements
        FeedItemClickCallback,
        View.OnClickListener,
        CreatingFeedCallback {

    @BindView(R.id.feedsRecyclerView)
    RecyclerView feedRecyclerView;
    @BindView(R.id.img_create_post)
    AppCompatImageView imgCreatePost;

    private Context mContext;
    private FeedsAdapter feedsAdapter;
    private List<Feeds> sample;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        sample = new ArrayList<>();
        feedsAdapter = new FeedsAdapter(mContext, this, this);
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
        ButterKnife.bind(this, view);
        feedRecyclerView.setHasFixedSize(true);
        feedRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        feedRecyclerView.addItemDecoration(new DividerItemDecoration(mContext));
        feedRecyclerView.setAdapter(feedsAdapter);
        imgCreatePost.setOnClickListener(this);

        feedsAdapter.add(getData());
    }

    private List<Feeds> getData() {
        Feeds feeds = new Feeds();
        feeds.setType(0);
        feeds.setDescription("I won't post the reversal animation so this article doesn't get enormous, but I am sure you can figure it out.");
        feeds.setDate("Jan 5");
        for (int i = 0; i < 20; i++) {
            sample.add(feeds);
        }
        return sample;
    }

    @Override
    public void commentOnFeed(Feeds feedItem) {
        Toast.makeText(mContext, "Commented", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void likeFeed(Feeds feedItem) {
        Toast.makeText(mContext, "Liked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeFeedView(int position) {
        feedsAdapter.removeCreateFeedView(position, imgCreatePost);
    }

    @Override
    public void createFeed(Feeds feeds, int position) {
        Toast.makeText(mContext, "Sharing.....", Toast.LENGTH_SHORT).show();
        feedsAdapter.removeCreateFeedView(position, imgCreatePost);
    }

    @Override
    public void onClick(View v) {
        Feeds newFeed = new Feeds();
        newFeed.setType(2);
        feedsAdapter.createFeed(newFeed);
        v.setVisibility(v.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }
}