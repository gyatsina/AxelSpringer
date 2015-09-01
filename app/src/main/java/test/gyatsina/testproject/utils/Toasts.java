package test.gyatsina.testproject.utils;

import android.content.Context;
import android.widget.Toast;

public class Toasts {
    public static void showShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(Context context, int messageResourceId) {
        Toast.makeText(context, messageResourceId, Toast.LENGTH_SHORT).show();
    }

}
