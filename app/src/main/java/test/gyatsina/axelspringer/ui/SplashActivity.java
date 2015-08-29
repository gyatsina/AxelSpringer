package test.gyatsina.axelspringer.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import test.gyatsina.axelspringer.R;

/**
 * Created by gyatsina
 */
public class SplashActivity extends Activity {
    private static final int TWO_SECONDS = 2 * 1000;
    private final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, TWO_SECONDS);
    }
}
