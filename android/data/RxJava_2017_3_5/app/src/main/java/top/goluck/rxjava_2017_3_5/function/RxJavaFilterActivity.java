package top.goluck.rxjava_2017_3_5.function;

import rx.Observable;
import rx.Subscriber;
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
        mObservable1.observeOn(Schedulers.newThread()).subscribe(getSubscriber("filter"));

        //takeLast
        Observable<Integer> mObservable2 = mObservable.takeLast(25);
        LogList("takeLast后的数据",mObservable2);
        mObservable2.observeOn(Schedulers.newThread()).subscribe(getSubscriber("takeLast"));

        //last
        Observable<Integer> mObservable3 = mObservable.last();
        LogList("last后的数据",mObservable3);
        mObservable3.observeOn(Schedulers.newThread()).subscribe(getSubscriber("last"));

        //lastOrDefault
        Observable<Integer> mObservable4 = mObservable.lastOrDefault(-100);
        LogList("lastOrDefault后的数据",mObservable4);


        mObservable4.observeOn(Schedulers.newThread()).subscribe(getSubscriber("lastOrDefault"));
    }
}