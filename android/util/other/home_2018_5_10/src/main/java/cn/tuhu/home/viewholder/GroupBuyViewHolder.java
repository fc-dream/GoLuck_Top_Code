package cn.tuhu.home.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.tuhu.home.R;
import cn.tuhu.home.adapter.CardAdapter;
import cn.tuhu.home.view.BannerScaleHelper;
import cn.tuhu.home.view.SpeedRecyclerView;

/**
 * 作者：luck on 2018/4/26 17:12
 * 邮箱：fc_dream@163.com
 * Tuhu_Android
 */
public class GroupBuyViewHolder extends RecyclerView.ViewHolder{

    private SpeedRecyclerView speed_recycler;

    public GroupBuyViewHolder(View itemView) {
        super(itemView);
        List<Integer> mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add(R.mipmap.ic_launcher);
        }
        speed_recycler = itemView.findViewById(R.id.speed_recycler);

//        final ScaleLayoutManager scaleLayoutManager = new ScaleLayoutManager(itemView.getContext(), 1, ScaleLayoutManager.HORIZONTAL);
//         scaleLayoutManager.setInfinite(true);
//        speed_recycler.setLayoutManager(scaleLayoutManager);


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        speed_recycler.setLayoutManager(linearLayoutManager);

        BannerScaleHelper mBannerScaleHelper = new BannerScaleHelper();
        mBannerScaleHelper.setFirstItemPos(1000);
        mBannerScaleHelper.attachToRecyclerView(speed_recycler);

        speed_recycler.setAdapter(new CardAdapter(mList));

//        new CardScaleLeftSnapHelper().attachToRecyclerView(speed_recycler);
    }

}
