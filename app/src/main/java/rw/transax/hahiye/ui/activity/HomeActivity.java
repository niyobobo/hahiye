package rw.transax.hahiye.ui.activity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import rw.transax.hahiye.R;
import rw.transax.hahiye.ui.fragment.FeedsFragment;
import rw.transax.hahiye.ui.fragment.HomeFragment;
import rw.transax.hahiye.ui.fragment.PlacesFragment;
import rw.transax.hahiye.ui.fragment.ProfileFragment;
import rw.transax.hahiye.ui.fragment.SearchFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navigationView = findViewById(R.id.home_navigation);
        navigationView.setOnNavigationItemSelectedListener(itemSelectedListener);
    }

    /**
     * Creating instance of BottomNavigationView.OnNavigationItemSelectedListener and
     * set it to OnNavigationItemSelectedListener method as parameter
     */
    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener =
            menuItem -> {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.menu_feed:
                        fragment = new FeedsFragment();
                        break;
                    case R.id.menu_search:
                        fragment = new SearchFragment();
                        break;
                    case R.id.menu_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.menu_places:
                        fragment = new PlacesFragment();
                        break;
                    case R.id.menu_account:
                        fragment = new ProfileFragment();
                        break;
                }
                return fragmentTransaction(fragment);
            };

    /**
     *  Make fragment transaction when a menu item on BottomNavigationView is clicked
     * @param fragment  Fragment need to be loaded on the screen
     * @return          Return true when fragment loaded otherwise false
     */
    private boolean fragmentTransaction(Fragment fragment) {
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
