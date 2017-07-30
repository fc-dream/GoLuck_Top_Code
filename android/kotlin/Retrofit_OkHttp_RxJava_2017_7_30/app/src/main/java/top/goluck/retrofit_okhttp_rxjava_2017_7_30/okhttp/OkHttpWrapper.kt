package top.goluck.retrofit_okhttp_rxjava_2017_7_30.okhttp

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Created by luck on 2017/7/30.
 */
class OkHttpWrapper {
    companion object {
        val okhttp : OkHttpClient
        init {
            okhttp = OkHttpClient().newBuilder()
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build()
        }
    }
}