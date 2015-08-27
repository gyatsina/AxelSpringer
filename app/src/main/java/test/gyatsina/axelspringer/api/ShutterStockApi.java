package test.gyatsina.axelspringer.api;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import test.gyatsina.axelspringer.models.DetailedItemById;
import test.gyatsina.axelspringer.models.DetailedItemsContainer;
import test.gyatsina.axelspringer.models.GamingItemInList;
import test.gyatsina.axelspringer.models.ItemsList;

/**
 * Created by gyatsina
 */
public interface ShutterStockApi {
    /*
    Usually backend request looks different from .php page
    So in general case "/" will be replaced with valuable method name, f.i. getList
     */
    @GET("/")
    void getGamingItemsList(@Query("controller") String controller, @Query("method") String getListMethod,
                            @Query("hub") String hub, @Query("lang") String lang,
                            @Query("batch") int batch,
                            Callback<ItemsList<GamingItemInList>> callback);

    @GET("/")
    void getDetailedItemsById(@Query("controller") String controller, @Query("method") String getListMethod,
                        @Query("ids") String ids,
                        Callback<DetailedItemsContainer<HashMap<Integer, DetailedItemById>>> callback);
}
