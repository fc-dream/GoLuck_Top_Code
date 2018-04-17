package top.goluck.lifecycle_aware2018_04_16.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

/**
 * 作者：luck on 2018/4/17 14:52
 * 邮箱：fc_dream@163.com
 * LifecycleAware2018_04_16
 */
public class TestViewModel extends ViewModel {

    private MutableLiveData<List<Test>> tests = new MutableLiveData<>();

    public LiveData<List<Test>> getTests() {
        return tests;
    }

    public void setTests(List<Test> data) {
        //非主线程赋值
        tests.postValue(data);
        //主线程赋值
        tests.setValue(data);
    }

}
