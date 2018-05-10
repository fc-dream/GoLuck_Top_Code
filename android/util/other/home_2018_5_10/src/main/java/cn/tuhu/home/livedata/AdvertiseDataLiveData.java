package cn.tuhu.home.livedata;

import android.arch.lifecycle.LiveData;

import java.util.List;

import cn.tuhu.home.model.HomeModel;

//
///**
// * 作者：luck on 2018/4/19 16:28
// * 邮箱：fc_dream@163.com
// * LifecycleAware2018_04_16
// */
public class AdvertiseDataLiveData extends LiveData<List<HomeModel>> {


    public AdvertiseDataLiveData() {
    }

    public void setLists(List<HomeModel> data) {
        this.setValue(data);
    }

    public LiveData<List<HomeModel>> getLists() {
        return this;
    }

//
//
//    @Override
//    protected void onActive() {
//        super.onActive();
//    }
//
//    @Override
//    protected void onInactive() {
//        super.onInactive();
//    }
//
//
}
