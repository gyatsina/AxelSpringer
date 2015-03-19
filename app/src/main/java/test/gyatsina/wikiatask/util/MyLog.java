package test.gyatsina.wikiatask.util;

import android.util.Log;

import test.gyatsina.wikiatask.WikiaFlags;


public class MyLog {

    public static void e(String tag, String message, Throwable tr) {

        if (WikiaFlags.LOG_ENABLED) {
            Log.e(tag, message, tr);
        }
    }

    public static void e(String tag, String message) {

        if (WikiaFlags.LOG_ENABLED) {
            Log.e(tag, message);
        }
    }

    public static void v(String tag, String message) {

        if (WikiaFlags.LOG_ENABLED) {
            Log.v("-------" + tag, message);
        }
    }

    public static void v(String tag, boolean message) {

        if (WikiaFlags.LOG_ENABLED) {
            Log.v("-------" + tag, Boolean.toString(message));
        }
    }

    public static void v(String tag, int message) {
        if (WikiaFlags.LOG_ENABLED) {
            Log.v("-------" + tag, Integer.toString(message));
        }
    }

    public static void v(String tag, CharSequence message) {
        if (WikiaFlags.LOG_ENABLED) {
            Log.v("-------" + tag, message.toString());
        }
    }

    public static void logv(String tag, String template, Object... args) {

        v(tag, format(template, args));
    }

    /**
     * Substitutes each {@code %s} in {@code template} with an argument. These are matched by position - the first
     * {@code %s} gets {@code args[0]}, etc. If there are more arguments than placeholders, the unmatched arguments will
     * be appended to the end of the formatted message in square braces.
     *
     * @param template a non-null string containing 0 or more {@code %s} placeholders.
     * @param args     the arguments to be substituted into the message template. Arguments are converted to strings
     *                 using {@link String#valueOf(Object)}. Arguments can be null.
     */
    private static String format(String template, Object... args) {

        template = String.valueOf(template); // null -> "null"
        // start substituting the arguments into the '%s' placeholders
        StringBuilder builder = new StringBuilder(template.length() + (16 * args.length));
        int templateStart = 0;
        int i = 0;
        while (i < args.length) {
            int placeholderStart = template.indexOf("%s", templateStart);
            if (placeholderStart == -1) {
                break;
            }
            builder.append(template.substring(templateStart, placeholderStart));
            builder.append(args[i++]);
            templateStart = placeholderStart + 2;
        }
        builder.append(template.substring(templateStart));
        // if we run out of placeholders, append the extra args in square braces
        if (i < args.length) {
            builder.append(" [");
            builder.append(args[i++]);
            while (i < args.length) {
                builder.append(", ");
                builder.append(args[i++]);
            }
            builder.append(']');
        }
        return builder.toString();
    }
}
