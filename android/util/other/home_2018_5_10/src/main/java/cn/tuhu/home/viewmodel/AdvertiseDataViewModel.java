package cn.tuhu.home.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import cn.tuhu.home.livedata.AdvertiseDataLiveData;
import cn.tuhu.home.model.HomeModel;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

//import net.tsz.afinal.common.HostType;
//import net.tsz.afinal.common.service.HomeService;
//import net.tsz.afinal.http.RetrofitManager;
//
//import cn.TuHu.Activity.MyHome.entity.AdvertiseData;
//import io.reactivex.Flowable;

/**
 * 作者：luck on 2018/4/17 14:52
 * 邮箱：fc_dream@163.com
 * LifecycleAware2018_04_16
 */
public class AdvertiseDataViewModel extends ViewModel {

    private AdvertiseDataLiveData mAdvertiseDataLiveData = new AdvertiseDataLiveData();
    private MutableLiveData<List<HomeModel>> studentMutableLiveData =new MutableLiveData<>();
//    private HomeService mHomeService;

    public AdvertiseDataViewModel() {
//        mHomeService = RetrofitManager.getInstance(HostType.HOST_TUHU_API)
//            .createService(HomeService.class);

        List<HomeModel> datas = new ArrayList<>();
        datas.add(new HomeModel(3, "我是测试数据111111"));
        datas.add(new HomeModel(4, "我是测试数据222222我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111"));
        datas.add(new HomeModel(4, "我是测试数据333333我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111"));
        datas.add(new HomeModel(5, "我是测试数据333333我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111我是测试数据111111"));
        studentMutableLiveData.setValue(datas);

    }

//    public Flowable<AdvertiseData> GetPrimeModule() {
//        return mHomeService.GetPrimeModule(null);
//    }

    public void setData(){
         Observable.just("1").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                List<HomeModel> datas = new ArrayList<>();
                datas.add(new HomeModel(0, ""));
                datas.add(new HomeModel(1, ""));
                datas.add(new HomeModel(2, ""));
                mAdvertiseDataLiveData.setLists(datas);
            }
        });
    }

    public AdvertiseDataLiveData getData(){
        return mAdvertiseDataLiveData;
    }

    public LiveData<List<HomeModel>> getLists2() {
        return studentMutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
//        mHomeService = null;
    }
}
