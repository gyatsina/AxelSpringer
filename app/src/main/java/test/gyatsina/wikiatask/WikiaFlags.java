package test.gyatsina.wikiatask;

public class WikiaFlags {

    public static final boolean DEV_MODE = BuildConfig.DEBUG;
    public static final boolean PROD_MODE = !DEV_MODE;
    public static final boolean LOG_ENABLED = true && DEV_MODE;
//    public static final boolean LOG_ENABLED = false;

    private WikiaFlags() {

        // static class with constants
    }
}
