package test.gyatsina.axelspringer.api;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import test.gyatsina.axelspringer.models.ImagesResponse;

/**
 * Created by gyatsina
 */
public interface ShutterStockApi {

    @GET("/v2/images/search")
    void getFlowerImages(@Query("query") String controller, @Query("per_page") int perPage, @Query("page") int page,
                         Callback<ImagesResponse> callback);
}
