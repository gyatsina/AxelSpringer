package test.gyatsina.wikiatask.api;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;

import java.net.CookieHandler;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RestAdapter.Log;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.OkClient;
import test.gyatsina.wikiatask.WikiaFlags;

import static test.gyatsina.wikiatask.util.MyLog.logv;

public class RetrofitWikiaApi {

    private static final String TAG = "RetrofitWikiaApi";
    public static final String STATUS_OK = "ok";
    public static final String CONTROLLER = "WikisApi";
    public static final String GET_LIST = "getList";
    public static final String GAMING_HUB = "Gaming";
    public static final String ENG_LANG = "en";
    private static final WikiaApi wikiaApi;
    private static Context context;

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
        wikiaApi = restAdapter.create(WikiaApi.class);

    }


    private RetrofitWikiaApi() {
        // This is a singleton
    }

    public static WikiaApi getWikiaApi(Context mContext) {
        context = mContext;
        if (wikiaApi == null) {
            throw new IllegalStateException("You need to call init function first!");
        }
        return wikiaApi;
    }
}
