package top.goluck.rxjava_2017_3_5.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import top.goluck.rxjava_2017_3_5.R;

/**
 * 作者：luck on 2017/2/8 17:48
 * 邮箱：fc_dream@163.com
 * Realm_2017_2_8
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected boolean isShowBack = false;
    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initConfigAndLayoutID());
        initActionBar();
        test = getView(R.id.test);
        initView();
    }

    //用于初始化配置
    //及用于指定当前Activity对应的layout
    protected abstract int initConfigAndLayoutID();

    protected abstract void initView();

    private void initActionBar() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(isShowBack);
            actionBar.setDisplayHomeAsUpEnabled(isShowBack);
        }
    }

    protected <T extends View> T getView(int viewId) {
        return (T) findViewById(viewId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    List<Subscriber> mSubscribers;

    protected Subscriber getSubscriber(final String type) {
        if (mSubscribers == null) {
            mSubscribers = new ArrayList<>();
        }
        Subscriber mSubscriber = new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                Log("---------------" + type + "-onCompleted");
                setText(test.getText().toString() + "--" + type + "-onCompleted" + "\n\n");
            }

            @Override
            public void onError(Throwable e) {
                Log("---------------" + type + "-onError-------e=" + e);
                setText(test.getText().toString()+"--" + type + "-onError-------e=" + e +"\n");

            }

            @Override
            public void onNext(Object s) {
                Log("---------------" + type + "-onNext-------s=" + s);
                setText(test.getText().toString()+"--" + type + "-onNext-------s=" + s +"\n");
            }
        };
        mSubscribers.add(mSubscriber);
        return mSubscriber;
    }

    private void setText(String text) {
        if(test!=null)
        test.setText(text);
    }

    protected void Log(String msg) {
        Log("Rx", msg);
    }

    protected void Log(String tag,String msg) {
        Log.i(tag, msg);
    }

    protected void LogList(final String msg, Observable<Integer> mObservable) {
        final Observable<List<Integer>> mObservables1 = mObservable.toList();//打印生成的数据
        mObservables1.subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                Log("List","-------------"+msg+"--size="+integers.size()+"-"+integers.toString());
                setText(test.getText().toString()+"--"+msg+"--size="+integers.size()+"-"+integers.toString() +"\n\n");
            }
        });
    }

    protected void LogListString(final String msg, Observable<String> mObservable) {
        final Observable<List<String>> mObservables1 = mObservable.toList();//打印生成的数据
        mObservables1.subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> integers) {
                Log("List","-------------"+msg+"--size="+integers.size()+"-"+integers.toString());
                setText(test.getText().toString()+"--"+msg+"--size="+integers.size()+"-"+integers.toString() +"\n\n");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscribers != null) {
            for (int i = 0; i < mSubscribers.size(); i++) {
                Subscriber Subscriber = mSubscribers.get(i);
                if (Subscriber != null && !Subscriber.isUnsubscribed()) {
                    Subscriber.unsubscribe();
                }
            }
        }
    }

}
