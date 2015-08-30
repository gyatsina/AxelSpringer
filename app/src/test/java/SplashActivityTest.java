package test.gyatsina.axelspringer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by Admin
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml")
public class SplashActivityTest {
    @Test
    public void testSomething() throws Exception {
        assertTrue(Robolectric.buildActivity(SplashActivityTest.class).create().get() != null);
    }
}
