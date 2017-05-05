package top.goluck.rxjava_2017_3_5.function;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import top.goluck.rxjava_2017_3_5.R;
import top.goluck.rxjava_2017_3_5.base.BaseActivity;

/**
 * 作者：luck on 2017/3/16 18:55
 * 邮箱：fc_dream@163.com
 * RxJava_2017_3_5
 */
public class RxJavaFilterActivity extends BaseActivity {
    @Override
    protected int initConfigAndLayoutID() {
        setTitle(R.string.main_filter);
        isShowBack = true;
        return R.layout.activity_rxjava_filter;
    }

    @Override
    protected void initView() {
        filter();
    }

    private void filter() {
        Observable<Integer> mObservable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 1; i <= 100; i++) {
//                    int randomInt = (new Random().nextInt(100000) % 100 +1) * i;//(1,100) *(1,100)
//                    subscriber.onNext(randomInt);

                    int num = i * i;//(1,100) *(1,100)
                    subscriber.onNext(num);
                }
                subscriber.onCompleted();
            }
        });
        LogList("生成的数据",mObservable);

        //filter
        Observable<Integer> mObservable1 = mObservable.filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer < 100 * 100 / 2;//过滤调这个数据中（min,max）大于中间数据的一半数据
            }
        });
        LogList("filter后的数据",mObservable1);
        //rx.exceptions.MissingBackpressureException  告诉我们，生产者太快了，我们的操作函数无法处理这种情况。  http://blog.chengyunfeng.com/?p=981
        // 方案1 .onBackpressureBuffer()  加缓冲
        // 方案2 .buffer(100, TimeUnit.MILLISECONDS) 当消费者忙的时候可以使用 buffer 和 window 操作函数来收集数据。如果批量处理数据速度比较快
        // 方案3 ..sample(100, TimeUnit.MILLISECONDS) sample 操作函数可以指定生产者发射数据的最大速度，多余的数据被丢弃了。 throttle 和 Debounce 也能实现类似的效果。
        mObservable1.buffer(100, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(getSubscriber("filter"));

        //takeLast
        Observable<Integer> mObservable2 = mObservable.takeLast(25);
        LogList("takeLast后的数据",mObservable2);
        mObservable2.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(getSubscriber("takeLast"));

        //last
        Observable<Integer> mObservable3 = mObservable.last();
        LogList("last后的数据",mObservable3);
        mObservable3.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(getSubscriber("last"));

        //lastOrDefault
        Observable<Integer> mObservable4 = mObservable.lastOrDefault(-100);
        LogList("lastOrDefault后的数据",mObservable4);


        mObservable4.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(getSubscriber("lastOrDefault"));
    }
}