package top.goluck.rxjava_2017_3_5.function;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import top.goluck.rxjava_2017_3_5.R;
import top.goluck.rxjava_2017_3_5.base.BaseActivity;

/**
 * 作者：luck on 2017/3/6 19:11
 * 邮箱：fc_dream@163.com
 * RxJava
 */
public class RxJavaCreateActivity extends BaseActivity {
    @Override
    protected int initConfigAndLayoutID() {
        setTitle(R.string.main_create);
        isShowBack = true;
        return R.layout.activity_rxjava_create;
    }

    @Override
    protected void initView() {
        create();
    }

    private void create() {
        //just
        String str = "T1";
        Observable<String> stringObservable1 = Observable.just(str);
        stringObservable1.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(getSubscriber("just"));

        //from
        String[] strs = {"1","T2","T33"};
        Observable<String> stringObservable2 = Observable.from(strs);
        stringObservable2.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(getSubscriber("from"));

        //create
        final Observable<String> stringObservable3 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("T1");
                subscriber.onCompleted();
            }
        });
        stringObservable3.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(getSubscriber("create"));


        //repeat  死循环
        String str4_ = "T1";
        Observable<String> stringObservable4_ = Observable.just(str4_);
        Observable<String> stringObservable4 = stringObservable4_.repeat();
        final Subscriber mSubscriber = getSubscriber("repeat");
        stringObservable4.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(mSubscriber);
        Observable.timer(10,TimeUnit.MICROSECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                mSubscriber.unsubscribe();
            }
        });

        //repeatWhen
        String str5_ = "T1";
        Observable<String> stringObservable5_ = Observable.just(str5_);
        Observable stringObservable5 = stringObservable5_.repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Void> observable) {
                return Observable.timer(30, TimeUnit.SECONDS);
            }
        });
        stringObservable5.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(getSubscriber("repeatWhen"));

        //defer
        Observable stringObservable6 = Observable.defer(new Func0<Observable<String>>(){
            @Override
            //注意此处的call方法没有Subscriber参数
            public Observable<String> call(){
                return Observable.just("T1");
            }
        });
        stringObservable6.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(getSubscriber("defer"));

        //range
        Observable stringObservable7 = Observable.range(1,5);
        stringObservable7.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(getSubscriber("defer"));

        //interval
        Observable stringObservable8 = Observable.interval(1,TimeUnit.MINUTES);
        stringObservable8.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(getSubscriber("defer"));
    }
}
