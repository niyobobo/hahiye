package rw.transax.hahiye.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.Objects;

import rw.transax.hahiye.R;
import rw.transax.hahiye.model.InterestModel;
import rw.transax.hahiye.ui.adapter.InterestAdapter;
import rw.transax.hahiye.utils.ItemOffsetDecoration;
import rw.transax.hahiye.viewModel.InterestViewModel;

public class InterestActivity extends AppCompatActivity implements InterestAdapter.InterestSelected {

    private InterestAdapter interestAdapter;
    private InterestViewModel viewModel;
    private FloatingActionButton mActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recycle_interests);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);
        ItemOffsetDecoration decoration = new ItemOffsetDecoration(this, R.dimen.small_margin);
        recyclerView.addItemDecoration(decoration);

        mActionButton = findViewById(R.id.action_button);

        interestAdapter = new InterestAdapter(this, this);
        viewModel = ViewModelProviders.of(this).get(InterestViewModel.class);
        viewModel.getAllInterests().observe(this, list -> interestAdapter.submitList(list));
        recyclerView.setAdapter(interestAdapter);

    }

    @Override
    public void onInterestSelected(InterestModel interest) {

        interest.setIsFollowed(interest.getIsFollowed() == 0 ? 1 : 0);
        viewModel.selectInterest(interest);
        int selected = viewModel.getTotalInterest();

        if (selected >= 2) mActionButton.show();
        else mActionButton.hide();

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
