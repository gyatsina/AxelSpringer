package test.gyatsina.axelspringer;

/**
 * Created by gyatsina
 */
public class AxelSpringerFlags {

    public static final boolean DEV_MODE = BuildConfig.DEBUG;
    public static final boolean PROD_MODE = !DEV_MODE;
    public static final boolean LOG_ENABLED = true && DEV_MODE;
//    public static final boolean LOG_ENABLED = false;

    private AxelSpringerFlags() {

        // static class with constants
    }
}
