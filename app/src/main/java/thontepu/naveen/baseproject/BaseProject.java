package thontepu.naveen.baseproject;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import thontepu.naveen.baseproject.Utilities.Constants;
import thontepu.naveen.baseproject.Utilities.SessionSharedPrefs;
import thontepu.naveen.baseproject.Utilities.Utilities;

/**
 * Created by mac on 12/22/16
 */

public class BaseProject extends Application {

    /**
     * The Shared preference is being initialize in the application class to make it as a Singleton Pattern.
     *
     * Registering Activity Lifecycle callbacks so as to know when an activity created and destroyed.
     * The present code will let you know when the application is in foreground and background so that you
     * can trigger some actions when the user moves of the application.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        SessionSharedPrefs.setInstance(this);
        Utilities.printLog("onCreate");
        registerActivityLifecycleCallbacks(new ActivityLifeCycle());
    }

    public class ActivityLifeCycle implements ActivityLifecycleCallbacks {
        String activityName;

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            activityName =activity.getComponentName().getClassName();
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {
            if (activityName.equalsIgnoreCase(activity.getComponentName().getClassName())){
                // TODO: 12/22/16 trigger actions when the user closes your application
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }
}
