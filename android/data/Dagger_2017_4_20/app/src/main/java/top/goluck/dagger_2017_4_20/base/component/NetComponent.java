package top.goluck.dagger_2017_4_20.base.component;

import javax.inject.Singleton;

import dagger.Component;
import top.goluck.dagger_2017_4_20.MainActivity;
import top.goluck.dagger_2017_4_20.base.module.AppModule;
import top.goluck.dagger_2017_4_20.base.module.NetModule;

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
   void inject(MainActivity activity);
}