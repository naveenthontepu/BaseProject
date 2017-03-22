package thontepu.naveen.baseproject.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import thontepu.naveen.baseproject.Utilities.SessionSharedPrefs;
import thontepu.naveen.baseproject.Utilities.Utilities;

/**
 * Created by mac on 1/9/17
 */

public class LaunchScreen extends Activity {

    // TODO: 1/9/17 Change the activity opening condition to your custom condition and change the launch screens as you wish
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent splash = Utilities.getIntent(this, SplashScreen.class);
        Intent main = Utilities.getIntent(this, MainActivity.class);
        Utilities.printLog("times opened = "+SessionSharedPrefs.getInstance().getTimesOpened());
        if (true || SessionSharedPrefs.getInstance().getTimesOpened() % 2 == 1) {
            startActivity(splash);
        } else {
            startActivity(main);
        }
        finish();
    }
}
