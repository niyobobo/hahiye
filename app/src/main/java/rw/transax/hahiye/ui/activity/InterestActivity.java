package rw.transax.hahiye.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.button.MaterialButton;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import rw.transax.hahiye.R;
import rw.transax.hahiye.model.InterestModel;
import rw.transax.hahiye.ui.adapter.InterestAdapter;
import rw.transax.hahiye.utils.ItemOffsetDecoration;
import rw.transax.hahiye.viewModel.InterestViewModel;

public class InterestActivity extends AppCompatActivity implements
        InterestAdapter.InterestSelected,
        View.OnClickListener {

    private InterestAdapter interestAdapter;
    private InterestViewModel viewModel;
    private MaterialButton mActionButton;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
        init();

        viewModel = ViewModelProviders.of(this).get(InterestViewModel.class);

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
        mActionButton.setOnClickListener(this);
        mRecyclerView = findViewById(R.id.recycle_interests);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(decoration);
    }

    @Override
    public void onInterestSelected(InterestModel interest) {
        interest.setFollowed(!interest.isFollowed());
        viewModel.selectInterest(interest);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_button:
                break;
            default:
                break;
        }
    }
}
