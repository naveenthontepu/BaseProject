package thontepu.naveen.baseproject.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import thontepu.naveen.baseproject.BuildConfig;

/**
 * Created by mac on 12/22/16
 */


/**
 *
 * Live template for the setter and getter method
 private static final String $variableName$ = "$variable$";
 public void set$variable$($object$ $variable$){
 editor.put$ObjectType$($variableName$ , $variable$);
 editor.commit();
 }

 public $object$ get$variable$(){
 return pref.get$ObjectType$($variableName$,$defaultvalue$);
 }
 */

public class SessionSharedPrefs {
    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;
    private static SessionSharedPrefs sessionSharedPrefs;
    private static final String PREF_FILENAME = BuildConfig.APPLICATION_ID;

    private static final String TRIAL_VARIABLE = "trialVariable";
    private static final String TIMES_OPENED = "timesOpened";


    private SessionSharedPrefs(Context context){
        pref= context.getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.apply();
    }
    public static void setInstance(Context context){
        sessionSharedPrefs = new SessionSharedPrefs(context);
    }

    public static SessionSharedPrefs getInstance(){
        return sessionSharedPrefs;
    }

    public void setTrialVariable(String trialVariable){
        editor.putString(TRIAL_VARIABLE , trialVariable);
        editor.commit();
    }

    public String getTrialVariable(){
        return pref.getString(TRIAL_VARIABLE,"");
    }

    public void setTimesOpened(int TimesOpened){
        editor.putInt(TIMES_OPENED, TimesOpened);
        editor.commit();
    }

    public int getTimesOpened(){
        return pref.getInt(TIMES_OPENED,0);
    }

}
