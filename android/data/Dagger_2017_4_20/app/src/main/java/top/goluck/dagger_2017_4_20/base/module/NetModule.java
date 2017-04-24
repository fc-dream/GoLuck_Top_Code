package top.goluck.dagger_2017_4_20.base.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import top.goluck.dagger_2017_4_20.base.api.GithubApiService;

@Module
public class NetModule {

    String mBaseUrl;
    Application application;
    @Named("Url")
    // Constructor needs one parameter to instantiate.
    public NetModule(String baseUrl, Application application) {
        this.mBaseUrl = baseUrl;
        this.application = application;
    }

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    // Application reference must come from AppModule.class
    SharedPreferences providesSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this.application);
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache() {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(this.application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        builder.cache(cache);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        builder.addInterceptor(logging);
        return builder.build();
    }



    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public GithubApiService provideGithubApiService(Retrofit retrofit) {
        return retrofit.create(GithubApiService.class);
    }
}
//    //如果服务器不支持缓存就可能没有指定这个头部，或者指定的值是如no-store等，但是我们还想在本地使用缓存的话要怎么办呢？这种情况下我们就需要使用Interceptor来重写Respose的头部信息，从而让okhttp支持缓存。
//    //如下所示，我们重写的Response的Cache-Control字段
//    public class CacheInterceptor implements Interceptor {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//            Response response = chain.proceed(request);
//            Response response1 = response.newBuilder()
//                    .removeHeader("Pragma")
//                    .removeHeader("Cache-Control")
//                    //cache for 30 days
//                    .header("Cache-Control", "max-age=" + 3600 * 24 * 30)
//                    .build();
//            return response1;
//        }
//    }