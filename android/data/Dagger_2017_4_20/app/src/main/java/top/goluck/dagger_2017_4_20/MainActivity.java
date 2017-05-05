package top.goluck.dagger_2017_4_20;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import top.goluck.dagger_2017_4_20.base.BaseApplication;
import top.goluck.dagger_2017_4_20.base.api.GithubApiService;
import top.goluck.dagger_2017_4_20.model.User;
import top.goluck.dagger_2017_4_20.model.UserResponse;

public class MainActivity extends AppCompatActivity {

    @Inject
    OkHttpClient mOkHttpClient;
    @Inject
    BaseApplication baseApplication;
    @Inject
    GithubApiService githubApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((BaseApplication)getApplication()).getNetComponent().inject(this);
        Timber.i("----------------mOkHttpClient==null" + (mOkHttpClient == null) + "baseApplication==null" + (baseApplication == null)+"githubApiService=null"+(githubApiService==null));
        Observable<UserResponse> mUserResponse = githubApiService.getUser("luck-fc");
        Timber.i("----------------mUserResponse==null"+(mUserResponse==null));
        Timber.i("----------------mUserResponse==null"+mUserResponse.toString());
        mUserResponse
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<UserResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e.getMessage());
            }

            @Override
            public void onNext(UserResponse userResponse) {
                Timber.i("----------------userResponse"+userResponse.toString());
            }
        });
        mUserResponse.map(User.UserResponseToUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<User>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e.getMessage());
            }

            @Override
            public void onNext(User user) {
                Timber.i("----------------user"+user.toString());
            }
        });
    }

}
