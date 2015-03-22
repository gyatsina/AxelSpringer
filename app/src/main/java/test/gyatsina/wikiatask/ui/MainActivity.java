package test.gyatsina.wikiatask.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import test.gyatsina.wikiatask.R;
import test.gyatsina.wikiatask.api.RetrofitWikiaApi;
import test.gyatsina.wikiatask.api.WikiaApi;
import test.gyatsina.wikiatask.models.DetailedItemById;
import test.gyatsina.wikiatask.models.DetailedItemsContainer;
import test.gyatsina.wikiatask.models.GamingItemInList;
import test.gyatsina.wikiatask.models.ItemsList;
import test.gyatsina.wikiatask.utils.MyLog;

/**
 * Created by gyatsina
 */
public class MainActivity extends BaseActivity {
    private static String CLASS_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GamingItemsListFragment categoriesPagerFragment = new GamingItemsListFragment();
        showFragment(categoriesPagerFragment, false);
    }

    public void showFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_frame, fragment);
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
