package top.goluck.rxjava_2017_3_5.function;

import java.util.ArrayList;
import java.util.List;

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
public class RxJavaTransformActivity extends BaseActivity {
    @Override
    protected int initConfigAndLayoutID() {
        setTitle(R.string.main_change);
        isShowBack = true;
        return R.layout.activity_rxjava_transform;
    }

    @Override
    protected void initView() {
        transform();
    }

    private void transform() {
        Observable<Integer> mObservable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 1; i <= 100; i++) {
                    int num = i * i;//(1,100) *(1,100)
                    subscriber.onNext(num);
                }
                subscriber.onCompleted();
            }
        });
        LogList("生成的数据", mObservable);

        //map
        Observable<String> mObservable1 = mObservable.map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "String" + integer;
            }
        });
        LogListString("map后的数据", mObservable1);
        //rx.exceptions.MissingBackpressureException  告诉我们，生产者太快了，我们的操作函数无法处理这种情况。  http://blog.chengyunfeng.com/?p=981
        mObservable1.observeOn(Schedulers.newThread()).subscribe(getSubscriber("map"));

        //flatMap
        Observable<String> mObservable2 = mObservable.toList().flatMap(new Func1<List<Integer>, Observable<String>>() {
            @Override
            public Observable<String> call(List<Integer> integers) {
                List<String> strs = new ArrayList<>();
                for (int i=0;i<integers.size();i++){
                    strs.add("flatMap"+integers.get(i));
                }
                return Observable.from(strs);
            }
        });
        LogListString("flatMap后的数据", mObservable2);
        mObservable2.observeOn(Schedulers.newThread()).subscribe(getSubscriber("flatMap"));


    }

}