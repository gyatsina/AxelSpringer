package test.gyatsina.axelspringer;

import android.app.Instrumentation;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.test.ActivityUnitTestCase;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;

import test.gyatsina.axelspringer.ui.MainActivity;

/**
 * Created by gyatsina
 */
public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {
    private MainActivity mActivity;
    private Instrumentation mInstrumentation;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        mInstrumentation = getInstrumentation();
        ContextThemeWrapper context = new ContextThemeWrapper(mInstrumentation.getTargetContext(), R.style.AppTheme);
        setActivityContext(context);
    }


    public void testActionBarTitle() {
        Context context = mInstrumentation.getTargetContext();
        context.setTheme(R.style.Theme_AppCompat);
        mActivity = launchActivity(context.getPackageName(),
                MainActivity.class, null);
        mInstrumentation.waitForIdleSync();

        ActionBar actionBar = mActivity.getSupportActionBar();
        String welcomeText = actionBar.getTitle().toString();
        assertTrue(welcomeText != mActivity.getResources().getString(R.string.app_name));

        sendKeys(KeyEvent.KEYCODE_BACK);
        mActivity.finish();

    }
}
