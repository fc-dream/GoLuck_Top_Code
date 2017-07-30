package top.goluck.retrofit_okhttp_rxjava_2017_7_30.base

import android.app.Application

/**
 * Created by luck on 2017/7/30.
 */
class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        context = this
    }


    companion object {
        var context: BaseApplication? = null
            private set
    }
}