package cn.tuhu.home;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * 作者：luck on 2018/4/26 17:29
 * 邮箱：fc_dream@163.com
 * Tuhu_Android
 */
public class BaseApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
