package rw.transax.hahiye.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.util.List;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import rw.transax.hahiye.R;
import rw.transax.hahiye.callback.InterestSelected;
import rw.transax.hahiye.model.InterestModel;
import rw.transax.hahiye.viewModel.InterestViewModel;

public class InterestActivity extends AppCompatActivity implements
        InterestSelected, View.OnClickListener {

    @BindView(R.id.action_button)
    ImageView mActionButton;
    @BindView(R.id.next_page)
    TextView mContinue;
    @BindView(R.id.chip_group)
    ChipGroup entryChipGroup;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private InterestViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
        init();
        viewModel = ViewModelProviders.of(this).get(InterestViewModel.class);
        viewModel.getObservableInterests().observe(this, list -> {
            if (list != null) displayInterestOnScreen(list);
        });

        viewModel.getTotalInterest().observe(this, integer -> {
            mActionButton.setVisibility(integer > 4 ? View.VISIBLE : View.GONE);
            mContinue.setText(integer > 4 ? getString(R.string.max_chip_selected) :
                    getString(R.string.select_at_least_5_interest));
        });
    }

    private void init() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        mActionButton.setOnClickListener(this);
    }

    private void displayInterestOnScreen(List<InterestModel> interestList) {
        entryChipGroup.removeAllViews();
        for (InterestModel interest : interestList) {
            final Chip customChip = newChip(interest.getName());
            if (interest.isFollowed()) customChip.setChecked(true);
            customChip.setOnClickListener(v -> this.onInterestSelected(interest));
            entryChipGroup.addView(customChip);
        }
    }

    private Chip newChip(String text) {
        final Chip chip = new Chip(this);
        int paddingDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 11, getResources().getDisplayMetrics());
        chip.setChipDrawable(ChipDrawable.createFromResource(this, R.xml.chip));
        chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
        chip.setText(text);
        chip.setCloseIconVisible(false);
        chip.setChipStrokeColorResource(R.color.colorButtonPressed);
        chip.setChipStrokeWidth(1);
        chip.setRippleColorResource(R.color.colorWhite);
        chip.setChipBackgroundColorResource(R.color.chip_selected);
        return chip;
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
                startActivity(new Intent(this, HomeActivity.class));
                break;
        }
    }
}
