package test.gyatsina.axelspringer.api;

import com.squareup.okhttp.OkHttpClient;

import java.net.CookieHandler;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RestAdapter.Log;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.OkClient;
import test.gyatsina.axelspringer.WikiaFlags;

import static test.gyatsina.axelspringer.utils.MyLog.logv;

/**
 * Created by gyatsina
 */
public class RetrofitShutterStockApi {

    private static final String TAG = "RetrofitWikiaApi";
    public static final String CONTROLLER = "WikisApi";
    public static final String GET_LIST = "getList";
    public static final String GET_DETAILS = "getDetails";
    public static final String GAMING_HUB = "Gaming";
    public static final String ENG_LANG = "en";
    private static final ShutterStockApi wikiaApi;

    static {
        LogLevel logLevel = WikiaFlags.LOG_ENABLED ? LogLevel.FULL : LogLevel.NONE;
        Log log = new Log() {

            @Override
            public void log(String logString) {

                logv(TAG, logString);
            }
        };
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade requestFacade) {

            }
        };
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setCookieHandler(CookieHandler.getDefault());
        OkClient okClient = new OkClient(okHttpClient);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(WikiaConfig.API_DOMEN)
                .setLogLevel(logLevel)
                .setClient(okClient)
                .setLog(log)
                .setRequestInterceptor(requestInterceptor)
                .build();
        wikiaApi = restAdapter.create(ShutterStockApi.class);

    }


    private RetrofitShutterStockApi() {
        // This is a singleton
    }

    public static ShutterStockApi getWikiaApi() {
        if (wikiaApi == null) {
            throw new IllegalStateException("You need to call init function first!");
        }
        return wikiaApi;
    }
}
