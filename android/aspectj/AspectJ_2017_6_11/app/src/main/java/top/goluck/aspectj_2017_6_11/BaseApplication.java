package top.goluck.aspectj_2017_6_11;

import android.app.Application;
import top.goluck.test.SdkManager;

/**
 * 作者：luck on 2017/6/13 15:14
 * 邮箱：fc_dream@163.com
 * AspectJ_2017_6_11
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SdkManager.init(this);
    }
}
