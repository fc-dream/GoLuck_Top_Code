package top.goluck.lifecycle_aware2018_04_16.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import top.goluck.lifecycle_aware2018_04_16.livedata.StringLiveData;

/**
 * 作者：luck on 2018/4/17 14:52
 * 邮箱：fc_dream@163.com
 * LifecycleAware2018_04_16
 */
public class TestViewModel extends ViewModel {

    private MutableLiveData<List<Test>> tests = new MutableLiveData<>();
    private StringLiveData a=new StringLiveData();

    public void setTests(List<Test> data) {
        //非主线程赋值
        tests.postValue(data);
        //主线程赋值
        tests.setValue(data);
    }

    public LiveData<List<Test>> getTests() {
        return tests;
    }

    public LiveData<List<String>> getTestNames() {
        LiveData<List<String>> userName = Transformations.map(tests, new android.arch.core.util.Function<List<Test>, List<String>>() {
            @Override
            public List<String> apply(List<Test> input) {
                List<String> datas = new ArrayList<>();
                for (int i = 0; i < input.size(); i++) {
                    datas.add(input.get(i).name);
                }
                return datas;
            }
        });
        return userName;
    }

    public StringLiveData getStringLiveDatas(){
        return a;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        a = null;
    }
}
