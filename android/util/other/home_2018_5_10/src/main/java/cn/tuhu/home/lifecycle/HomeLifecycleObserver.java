package cn.tuhu.home.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.OnLifecycleEvent;

import io.reactivex.disposables.CompositeDisposable;


/**
 * 作者：luck on 2018/4/16 15:57
 * 邮箱：fc_dream@163.com
 *Aware2018_04_16
 */
public class HomeLifecycleObserver implements LifecycleObserver {


    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private LifecycleRegistry mLifecycle;

    public HomeLifecycleObserver(LifecycleRegistry lifecycleRegistry){
        mLifecycle = lifecycleRegistry;
        mLifecycle.addObserver(this);
//        ViewModelProviders.of(this, mViewModelFactory).get(UserViewModel.class);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
//        mDisposable.add();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        mLifecycle.removeObserver(this);
        mDisposable.clear();
    }

}
