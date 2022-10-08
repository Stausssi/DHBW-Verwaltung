package dhbw.verwaltung.util;

import android.content.Context;
import android.widget.Toast;

public class OnScreenNotificationHandler {

    private OnScreenNotificationHandler() {}

    public static void displayToast(Context context, String message, int duration) {
        Toast.makeText(context, message, duration).show();
    }
    public static void displayToast(Context context, String message, int duration, int gravity, int x, int y) {
        Toast toast = Toast.makeText(context, message, duration);
        toast.setGravity(gravity, x, y);
        toast.show();
    }
}
