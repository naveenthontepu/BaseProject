package thontepu.naveen.baseproject.Utilities;

import android.content.Context;
import android.os.Vibrator;

/**
 * Created by mac on 3/24/17
 */

public class SensorUtilities {
    /**
     * Triggers vibration for the android device if it is present.
     * Vibrate constantly for the specified period of time.
     * <p>This method requires the caller to hold the permission
     * {@link android.Manifest.permission#VIBRATE}.
     * <p>Usage in SplashScreen startVibration(View view)
     * @param context send in Application context as you could get the same vibrator instance everytime.
     * @param time    time in milliseconds
     * @required Vibration permission
     */
    public static void triggerVibration(Context context, long time) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(time);
        }
    }

    /**
     * Cancels vibration of the android device.
     * <p>This method requires the caller to hold the permission
     * {@link android.Manifest.permission#VIBRATE}.
     * <p>Usage in MainActivity stopVibration()
     * @param context send in Application context as you could get the same vibrator instance everytime.
     */
    public static void cancelVibration(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator.hasVibrator()) {
            vibrator.cancel();
        }
    }
}
