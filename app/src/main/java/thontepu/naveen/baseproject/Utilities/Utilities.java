package thontepu.naveen.baseproject.Utilities;

import android.util.Log;

import thontepu.naveen.baseproject.BuildConfig;

/**
 * Created by mac on 12/22/16
 */

public class Utilities {
    public static void printLog(String msg) {
        printLog(Constants.Tags.TAG, msg);
    }

    public static void printLog(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            int max = 3000;
            if (msg.length()<max) {
                Log.i(tag, msg);
            }else {
                int len = msg.length();
                for (int i=0;i<len;i+=max){
                    Log.i(tag, msg.substring(i,(i+max)<len?i+max:len));
                }
            }
        }
    }
}
