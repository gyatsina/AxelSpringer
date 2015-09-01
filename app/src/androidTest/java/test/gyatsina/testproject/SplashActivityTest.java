package test.gyatsina.testproject;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.TextView;

import test.gyatsina.testproject.ui.SplashActivity;

/**
 * Created by gyatsina
 */
public class SplashActivityTest extends ActivityUnitTestCase<SplashActivity> {
    private SplashActivity mActivity;
    private Instrumentation mInstrumentation;

    public SplashActivityTest() {
        super(SplashActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        mInstrumentation = getInstrumentation();
        Intent mLaunchIntent = new Intent(getInstrumentation()
                .getTargetContext(), SplashActivity.class);
        startActivity(mLaunchIntent, null, null);

        mActivity = getActivity();
    }


    public void testTextAvailability() {
        TextView textView = (TextView) mActivity.findViewById(R.id.welcome_text);
        String welcomeText = textView.getText().toString();
        assertTrue(welcomeText != "");
    }
}
