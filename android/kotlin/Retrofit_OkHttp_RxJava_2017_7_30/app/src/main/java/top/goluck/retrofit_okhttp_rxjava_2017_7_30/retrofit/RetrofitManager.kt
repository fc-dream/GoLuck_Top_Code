package top.goluck.retrofit_okhttp_rxjava_2017_7_30.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import top.goluck.retrofit_okhttp_rxjava_2017_7_30.okhttp.OkHttpWrapper
import com.google.gson.GsonBuilder



/**
 * Created by luck on 2017/7/30.
 */
class RetrofitManager() {
    private val retrofitManagers = HashMap<String,RetrofitManager>()
    private lateinit var mRetrofit: Retrofit
    constructor(baseUrl: String) : this() {
        var retrofitManager = retrofitManagers.get(baseUrl)
        if (retrofitManager == null) {
            val gson = GsonBuilder()
                    //配置你的Gson
                    .setDateFormat("yyyy-MM-dd hh:mm:ss")
                    .create()
            retrofitManager = RetrofitManager()
            retrofitManager.mRetrofit = Retrofit.Builder()
                    .client(OkHttpWrapper.okhttp)
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            retrofitManagers.put(baseUrl, retrofitManager)
        }
        mRetrofit = retrofitManager.mRetrofit
    }
    /**
     * @param service 服务接口
     * @return T
     */
    fun <T> createService(service: Class<T>): T {
        return mRetrofit.create(service)
    }
}