package top.goluck.dagger_2017_4_20.base.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import top.goluck.dagger_2017_4_20.base.BaseApplication;

@Module
public class AppModule {

    BaseApplication mBaseApplication;

    public AppModule(BaseApplication baseApplication) {
        mBaseApplication = baseApplication;
    }

    @Provides
    @Singleton
    BaseApplication providesBaseApplication() {
        return mBaseApplication;
    }
}