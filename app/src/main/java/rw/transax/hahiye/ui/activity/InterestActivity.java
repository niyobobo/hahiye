package rw.transax.hahiye.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import rw.transax.hahiye.R;
import rw.transax.hahiye.model.InterestModel;
import rw.transax.hahiye.ui.adapter.InterestAdapter;
import rw.transax.hahiye.viewModel.InterestViewModel;

public class InterestActivity extends AppCompatActivity implements InterestAdapter.InterestSelected {

    private InterestAdapter interestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        InterestViewModel viewModel = ViewModelProviders.of(this).get(InterestViewModel.class);
        interestAdapter = new InterestAdapter(viewModel.getInterests().getValue(), this, this);

        RecyclerView recyclerView = findViewById(R.id.recycle_interests);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(interestAdapter);

        viewModel.getInterests().observe(this, list -> interestAdapter.setData(list));

    }

    @Override
    public void onInterestSelected(InterestModel interest) {
        /*
         * Adding interest to ChipGroup
         */
        Toast.makeText(this, interest.getName(), Toast.LENGTH_SHORT).show();
    }


}
