package rw.transax.hahiye.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class AppUtils {

    public static void showToast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
