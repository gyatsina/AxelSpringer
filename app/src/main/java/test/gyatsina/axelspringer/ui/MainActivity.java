package test.gyatsina.axelspringer.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import test.gyatsina.axelspringer.R;

/**
 * Created by gyatsina
 */
public class MainActivity extends BaseActivity {
    private static String CLASS_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            ItemsListFragment gamingFragment = new ItemsListFragment();
            showFragment(gamingFragment, false, ItemsListFragment.CLASS_TAG);
        } else {
            ItemsListFragment gamingFragment = (ItemsListFragment) getSupportFragmentManager().findFragmentByTag(ItemsListFragment.CLASS_TAG);
        }
    }

    public void showFragment(Fragment fragment, boolean addToBackStack, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_frame, fragment, tag);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
//        transaction.commitAllowingStateLoss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
