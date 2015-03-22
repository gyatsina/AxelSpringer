package test.gyatsina.wikiatask.api;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import test.gyatsina.wikiatask.models.DetailedItemById;
import test.gyatsina.wikiatask.models.DetailedItemsContainer;
import test.gyatsina.wikiatask.models.GamingItemInList;
import test.gyatsina.wikiatask.models.ItemsList;

/**
 * Created by gyatsina
 */
public interface WikiaApi {
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
