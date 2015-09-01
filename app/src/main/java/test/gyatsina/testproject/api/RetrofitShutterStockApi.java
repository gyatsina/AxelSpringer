package test.gyatsina.testproject.api;

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
import test.gyatsina.testproject.TestFlags;

import static test.gyatsina.testproject.utils.MyLog.logv;

/**
 * Created by gyatsina
 */

/* This is basic API class
It creates OkHttpClient, attaches header
Header for requests is saved in AndroidManifest.
It represents form: (CLIENT_ID:CLIENT_SECRET) encoded in Base64
*/
public class RetrofitShutterStockApi {

    private static final String TAG = RetrofitShutterStockApi.class.getName();
    public static final String FLOWER_QUERY = "flower";
    public static final int DEFAULT_PAGE = 1;
    public static final int PER_PAGE = 10;
    public static final int LIMIT = 100;

    private static final ShutterStockApi shutterStockApi;
    private static Context context;

    static {
        LogLevel logLevel = TestFlags.LOG_ENABLED ? LogLevel.FULL : LogLevel.NONE;
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

                        requestFacade.addHeader("Authorization", "Basic " + base64IdSecret);
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

    public static ShutterStockApi getShutterStockApi(Context mContext) {
        context = mContext;
        if (shutterStockApi == null) {
            throw new IllegalStateException("You need to call init function first!");
        }
        return shutterStockApi;
    }
}
