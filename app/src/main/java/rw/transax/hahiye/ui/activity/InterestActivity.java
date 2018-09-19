package rw.transax.hahiye.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.Objects;

import rw.transax.hahiye.R;
import rw.transax.hahiye.model.InterestModel;
import rw.transax.hahiye.ui.adapter.InterestAdapter;
import rw.transax.hahiye.utils.ItemOffsetDecoration;
import rw.transax.hahiye.viewModel.InterestViewModel;

public class InterestActivity extends AppCompatActivity implements InterestAdapter.InterestSelected {

    private InterestAdapter interestAdapter;
    private InterestViewModel viewModel;
    private MaterialButton mActionButton;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
        init();

        InterestViewModel.Factory factory = new InterestViewModel.Factory(getApplication());
        viewModel = ViewModelProviders.of(this, factory).get(InterestViewModel.class);

        viewModel.getObservableInterests().observe(this, list ->
                interestAdapter.submitList(list));

        viewModel.getTotalInterest().observe(this, integer ->
                mActionButton.setVisibility(integer > 2 ? View.VISIBLE : View.GONE));

        interestAdapter = new InterestAdapter(this, this);
        interestAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(interestAdapter);

    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        ItemOffsetDecoration decoration = new ItemOffsetDecoration(this, R.dimen.small_margin);

        mActionButton = findViewById(R.id.action_button);
        mRecyclerView = findViewById(R.id.recycle_interests);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(decoration);
    }

    @Override
    public void onInterestSelected(InterestModel interest) {
        interest.setIsFollowed(interest.getIsFollowed() == 0 ? 1 : 0);
        viewModel.selectInterest(interest);

        //viewModel.deleteInterest(interest);
        //viewModel.addInterest(new InterestModel(UUID.randomUUID().toString(), "hello", "https://api.androidhive.info/images/food/5.jpg"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return true;
    }
}
