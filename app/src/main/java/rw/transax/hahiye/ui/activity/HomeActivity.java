package rw.transax.hahiye.ui.activity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import rw.transax.hahiye.R;
import rw.transax.hahiye.ui.fragment.FeedsFragment;
import rw.transax.hahiye.ui.fragment.HomeFragment;
import rw.transax.hahiye.ui.fragment.PlacesFragment;
import rw.transax.hahiye.ui.fragment.ProfileFragment;
import rw.transax.hahiye.ui.fragment.SearchFragment;

public class HomeActivity extends AppCompatActivity {

    private final String SELECTED_FRAGMENT = "SELECTED_FRAGMENT";
    private Fragment mFragment;

    @BindView(R.id.home_navigation)
    BottomNavigationView bottomNavigationView;

    /**
     * Creating instance of BottomNavigationView.OnNavigationItemSelectedListener and
     * set it to OnNavigationItemSelectedListener method as parameter
     */
    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener =
            menuItem -> {
                switch (menuItem.getItemId()) {
                    case R.id.menu_feed:
                        mFragment = new FeedsFragment();
                        break;
                    case R.id.menu_search:
                        mFragment = new SearchFragment();
                        break;
                    case R.id.menu_home:
                        mFragment = new HomeFragment();
                        break;
                    case R.id.menu_places:
                        mFragment = new PlacesFragment();
                        break;
                    case R.id.menu_account:
                        mFragment = new ProfileFragment();
                        break;
                }
                return fragmentTransaction(mFragment);
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(itemSelectedListener);

        if (savedInstanceState != null)
            mFragment = getSupportFragmentManager().getFragment(savedInstanceState, SELECTED_FRAGMENT);

        fragmentTransaction(mFragment != null ? mFragment : new FeedsFragment());
    }

    /**
     * Save state of selected fragment so when activity restarted
     * the instance will be restored.
     */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, SELECTED_FRAGMENT, mFragment);
    }

    /**
     * Make mFragment transaction when a menu item on BottomNavigationView is clicked
     *
     * @param fragment Fragment need to be loaded on the screen
     * @return Return true when mFragment loaded otherwise false
     */
    private boolean fragmentTransaction(Fragment fragment) {
        mFragment = fragment;
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
