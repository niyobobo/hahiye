package rw.transax.hahiye.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import rw.transax.hahiye.R;
import rw.transax.hahiye.model.InterestModel;
import rw.transax.hahiye.ui.adapter.InterestAdapter;
import rw.transax.hahiye.viewModel.InterestViewModel;

public class InterestActivity extends AppCompatActivity implements InterestAdapter.InterestSelected {

    private InterestAdapter interestAdapter;
    private InterestViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        RecyclerView recyclerView = findViewById(R.id.recycle_interests);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        interestAdapter = new InterestAdapter(this, this);
        viewModel = ViewModelProviders.of(this).get(InterestViewModel.class);
        viewModel.getAllInterests().observe(this, list -> interestAdapter.submitList(list));
        recyclerView.setAdapter(interestAdapter);

    }

    @Override
    public void onInterestSelected(InterestModel interest) {

        interest.setIsFollowed(interest.getIsFollowed() == 0 ? 1 : 0);
        viewModel.selectInterest(interest);

        Toast.makeText(this, String.valueOf(viewModel.getTotalInterest()), Toast.LENGTH_SHORT).show();

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
