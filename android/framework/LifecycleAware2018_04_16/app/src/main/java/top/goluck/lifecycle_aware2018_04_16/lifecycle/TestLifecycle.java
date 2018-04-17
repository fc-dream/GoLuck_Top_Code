package top.goluck.lifecycle_aware2018_04_16.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProviders;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import top.goluck.lifecycle_aware2018_04_16.MainActivity;
import top.goluck.lifecycle_aware2018_04_16.viewmodel.Test;
import top.goluck.lifecycle_aware2018_04_16.viewmodel.TestViewModel;

/**
 * 作者：luck on 2018/4/16 15:57
 * 邮箱：fc_dream@163.com
 *Aware2018_04_16
 */
public class TestLifecycle implements LifecycleObserver {

    private LifecycleRegistry mLifecycle;

    public TestLifecycle(MainActivity context){

        TestViewModel model = ViewModelProviders.of(context).get(TestViewModel.class);
        List<Test> datas = new ArrayList<>();
        datas.add(new Test(1,"测试数据1"));
        datas.add(new Test(2,"测试数据2"));
        model.setTests(datas);
        mLifecycle = context.getLifecycle();
        mLifecycle.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Log.e("test","WishScoreRelativeLayout.onCreate()" );

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Log.e("test","WishScoreRelativeLayout.onStart()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.e("test","WishScoreRelativeLayout.onResume()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.e("test","WishScoreRelativeLayout.onStop()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        Log.e("test","WishScoreRelativeLayout.onDestroy()" );
        mLifecycle.removeObserver(this);
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void OnAny() {
        Log.e("test","WishScoreRelativeLayout.OnAny()" );
    }
}
