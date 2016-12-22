package thontepu.naveen.baseproject.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mac on 12/22/16
 */

public class SessionSharedPrefs {
    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;
    private static SessionSharedPrefs sessionSharedPrefs;

    private static final String PREF_FILENAME = "thontepu.naveen.baseproject";


    private SessionSharedPrefs(Context context){
        pref= context.getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.apply();
    }
    public static void setInstance(Context context){
        sessionSharedPrefs = new SessionSharedPrefs(context);
    }


}
