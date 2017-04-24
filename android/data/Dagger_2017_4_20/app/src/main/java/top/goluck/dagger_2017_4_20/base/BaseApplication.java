package top.goluck.dagger_2017_4_20.base;

import android.app.Application;

import timber.log.Timber;
import top.goluck.dagger_2017_4_20.base.component.DaggerNetComponent;
import top.goluck.dagger_2017_4_20.base.component.NetComponent;
import top.goluck.dagger_2017_4_20.base.module.AppModule;
import top.goluck.dagger_2017_4_20.base.module.NetModule;

/**
 * 作者：luck on 2017/4/21 14:57
 * 邮箱：fc_dream@163.com
 * Dagger_2017_4_20
 */
public class BaseApplication extends Application {

    private NetComponent mNetComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        // Dagger %COMPONENT_NAME%
        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule("https://api.github.com",this))
                .build();
        // If a Dagger 2 component does not have any constructor arguments for any of its modules,
        // then we can use .create() as a shortcut instead:
        //  mNetComponent = com.codepath.dagger.components.DaggerNetComponent.create();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
