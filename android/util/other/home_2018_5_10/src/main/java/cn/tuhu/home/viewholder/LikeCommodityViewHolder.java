package cn.tuhu.home.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.tuhu.home.R;
import cn.tuhu.home.view.ScreenUtil;

/**
 * 作者：luck on 2018/4/28 15:59
 * 邮箱：fc_dream@163.com
 * Tuhu_Android
 */
public class LikeCommodityViewHolder extends RecyclerView.ViewHolder {

    private ImageView like_commodity_img1;
    private TextView like_commodity_text1, like_commodity_text2, like_commodity_text3, like_commodity_text4, like_commodity_text5;

    public LikeCommodityViewHolder(View itemView) {
        super(itemView);
        like_commodity_img1 = itemView.findViewById(R.id.like_commodity_img1);
        like_commodity_text1 = itemView.findViewById(R.id.like_commodity_text1);
        like_commodity_text2 = itemView.findViewById(R.id.like_commodity_text2);
        like_commodity_text3 = itemView.findViewById(R.id.like_commodity_text3);
        like_commodity_text4 = itemView.findViewById(R.id.like_commodity_text4);
        like_commodity_text5 = itemView.findViewById(R.id.like_commodity_text5);
        int width = ScreenUtil.getScreenWidth(itemView.getContext());
        int dp15 = ScreenUtil.dip2px(itemView.getContext(), 15);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((width - dp15) / 2,(width - dp15) / 2 );
        like_commodity_img1.setLayoutParams(layoutParams);
    }

}