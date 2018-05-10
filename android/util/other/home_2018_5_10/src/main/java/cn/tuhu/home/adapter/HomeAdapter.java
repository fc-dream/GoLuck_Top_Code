package cn.tuhu.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import java.util.ArrayList;
import java.util.List;

import cn.tuhu.home.R;
import cn.tuhu.home.model.HomeModel;
import cn.tuhu.home.viewholder.EveryDaySeckillViewHolder;
import cn.tuhu.home.viewholder.FreeViewHolder;
import cn.tuhu.home.viewholder.GroupBuyViewHolder;

/**
 * 作者：luck on 2016/11/1 15:24
 * 邮箱：fc_dream@163.com
 * Tuhu_Android 首页适配器
 * ########################################################
 * ItemViewType     BaseViewHolder          type
 * 0                HomeMainViewHolder   主业务
 * 1                HomeSplitViewHolder     4金刚、9宫格 自定义，有无标题          纯图 分区域点击 加 有无标题
 * 2                HomeIMGSwitchViewHolder 一美人，多图可滑动切换                 可以左右滑动的
 * 3                HomeSeckillViewHolder   小卖铺                                 天天秒杀
 * 4                HomeNewUserViewHolder   新手区                                 新手专区 一张图
 * 5                HomeHotNewViewHolder   老司机                                  汽车头条
 * 6                HomeRemindViewHolder   小蜜                                    保养提醒
 * 7                HomeFoundViewHolder   发现                                     发现列表
 * 8                HomeMHeadViewHolder   发现标题                                 发现标题
 * 9                HeadViewHolder   占位                                          占位
 * 10               HomeScrollTimeViewHolder 瀑布流有倒计时                        瀑布流有倒计时
 * 11               HomeScrollViewHolder   瀑布流无倒计时                          瀑布流无倒计时
 * 12               HomeRowsViewHolder     动画模块                                营销模块
 * 13               HomeRemindTrieViewHolder 推荐轮胎
 * 14               HomeRemindMaintainViewHolder 推荐保养
 * 15               HomeRemindRefitViewHolder 推荐改装
 * 16               HomeRemindBeautyBuyViewHolder 推荐美容
 * ######################################################
 */
public class HomeAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder>{

    private List<HomeModel> datas = new ArrayList<>();
    public void addDatas(List<HomeModel> datas){
        this.datas.addAll(datas);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if(viewType==0){
            viewHolder = new GroupBuyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_groupbuy, parent, false));
        }else if(viewType==1){
            viewHolder = new EveryDaySeckillViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_everydayseckill, parent, false));
        }else if(viewType==2){
            viewHolder = new FreeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_free, parent, false));
        }else{
            viewHolder = new GroupBuyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_groupbuy, parent, false));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).type;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }
}