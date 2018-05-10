package cn.tuhu.home.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.tuhu.home.R;
import cn.tuhu.home.view.ScreenUtil;

/**
 * 作者：luck on 2018/4/28 15:59
 * 邮箱：fc_dream@163.com
 * Tuhu_Android
 */
public class LikeBannerViewHolder extends RecyclerView.ViewHolder {

    private ImageView like_banner_img1;

    public LikeBannerViewHolder(View itemView) {
        super(itemView);
        like_banner_img1 = itemView.findViewById(R.id.like_banner_img1);
        int width = ScreenUtil.getScreenWidth(itemView.getContext());
        int dp15 = ScreenUtil.dip2px(itemView.getContext(),15);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams((width - dp15) / 2,(width - dp15) / 2 * 225 / 173);
        like_banner_img1.setLayoutParams(layoutParams);
    }

}