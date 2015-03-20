package test.gyatsina.wikiatask;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import test.gyatsina.wikiatask.api.RetrofitWikiaApi;
import test.gyatsina.wikiatask.api.WikiaApi;
import test.gyatsina.wikiatask.models.DetailedItemById;
import test.gyatsina.wikiatask.models.DetailedItemsContainer;
import test.gyatsina.wikiatask.models.GamingItemInList;
import test.gyatsina.wikiatask.models.ItemsList;
import test.gyatsina.wikiatask.util.MyLog;


public class MainActivity extends ActionBarActivity {
    private static String CLASS_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getGamingItemsList();
        getDetailedItemsById();
    }

    public void getGamingItemsList() {
        WikiaApi wikiaApi = RetrofitWikiaApi.getWikiaApi(this);
        wikiaApi.getGamingItemsList(RetrofitWikiaApi.CONTROLLER, RetrofitWikiaApi.GET_LIST, RetrofitWikiaApi.GAMING_HUB,
                RetrofitWikiaApi.ENG_LANG, 25, new Callback<ItemsList<GamingItemInList>>() {
                    @Override
                    public void success(ItemsList<GamingItemInList> response, Response response2) {
                        MyLog.v(CLASS_TAG, "getGamingItemsList " + response.getItems().get(0).getName());
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        MyLog.v(CLASS_TAG, "Failed to get gaming items list: " + retrofitError);
                    }
                });
    }

    public void getDetailedItemsById() {
        String testIds = "3125,490";
        WikiaApi wikiaApi = RetrofitWikiaApi.getWikiaApi(this);
        wikiaApi.getDetailedItemsById(RetrofitWikiaApi.CONTROLLER, RetrofitWikiaApi.GET_DETAILS,
                testIds, new Callback<DetailedItemsContainer<HashMap<Integer, DetailedItemById>>>() {
                    @Override
                    public void success(DetailedItemsContainer<HashMap<Integer, DetailedItemById>> response, Response response2) {
                        Integer iInteger = new Integer(490);
                        MyLog.v(CLASS_TAG, "getGamingItemsList " + response.getItems().get(iInteger).getImage());
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        MyLog.v(CLASS_TAG, "Failed to get gaming items list: " + retrofitError);
                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
