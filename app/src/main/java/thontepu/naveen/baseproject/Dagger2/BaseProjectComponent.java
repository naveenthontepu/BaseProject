package thontepu.naveen.baseproject.Dagger2;

import javax.inject.Singleton;

import dagger.Component;
import thontepu.naveen.baseproject.Activities.SplashScreen;
import thontepu.naveen.baseproject.Dagger2.Modules.BaseProjectModule;

/**
 * Created by mac on 3/30/17
 */
@Singleton
@Component(modules = {BaseProjectModule.class})
public interface BaseProjectComponent {
    void inject(SplashScreen activity);
}
