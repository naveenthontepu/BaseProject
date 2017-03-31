package thontepu.naveen.baseproject.Dagger2.Modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import thontepu.naveen.baseproject.BaseProject;

/**
 * Created by mac on 3/30/17
 */

@Module
public class BaseProjectModule {
    private final BaseProject baseProject;

    public BaseProjectModule(BaseProject baseProject) {
        this.baseProject = baseProject;
    }

    @Provides
    @Singleton
    BaseProject provideBaseProject() {
        return baseProject;
    }
}
