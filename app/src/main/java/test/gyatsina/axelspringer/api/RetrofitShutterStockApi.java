package test.gyatsina.axelspringer.api;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

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

    private static final String TAG = RetrofitShutterStockApi.class.getName();
    public static final String FLOWER_QUERY = "flower";
    public static final int DEFAULT_PER_PAGE = 10;

    public static final String CONTROLLER = "WikisApi";
    public static final String GET_LIST = "getList";
    public static final String GET_DETAILS = "getDetails";
    public static final String GAMING_HUB = "Gaming";
    public static final String ENG_LANG = "en";
    private static final ShutterStockApi shutterStockApi;
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
                if (context != null) {
                    try {
                        ApplicationInfo app = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                        Bundle bundle = app.metaData;
                        String base64IdSecret = bundle.getString(ShutterStockConfig.BASE64_ID_SECRET);

                        requestFacade.addHeader("Authorization", "Basic "+base64IdSecret);
                    } catch (PackageManager.NameNotFoundException e) {
                        return;
                    }
                }
            }
        };
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setCookieHandler(CookieHandler.getDefault());
        OkClient okClient = new OkClient(okHttpClient);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ShutterStockConfig.API_DOMEN)
                .setLogLevel(logLevel)
                .setClient(okClient)
                .setLog(log)
                .setRequestInterceptor(requestInterceptor)
                .build();
        shutterStockApi = restAdapter.create(ShutterStockApi.class);

    }


    private RetrofitShutterStockApi() {
        // This is a singleton
    }

    public static ShutterStockApi getWikiaApi(Context mContext) {
        context = mContext;
        if (shutterStockApi == null) {
            throw new IllegalStateException("You need to call init function first!");
        }
        return shutterStockApi;
    }
}
