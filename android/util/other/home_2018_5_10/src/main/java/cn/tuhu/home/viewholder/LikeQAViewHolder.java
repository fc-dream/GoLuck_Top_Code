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
public class LikeQAViewHolder extends RecyclerView.ViewHolder {

    private ImageView like_qa_img1;
    private TextView like_qa_text1,like_qa_text2,like_qa_text3,like_qa_text4;

    public LikeQAViewHolder(View itemView) {
        super(itemView);
        like_qa_img1 = itemView.findViewById(R.id.like_qa_img1);
        like_qa_text1 = itemView.findViewById(R.id.like_qa_text1);
        like_qa_text2 = itemView.findViewById(R.id.like_qa_text2);
        like_qa_text3 = itemView.findViewById(R.id.like_qa_text3);
        like_qa_text4 = itemView.findViewById(R.id.like_qa_text4);
        int width = ScreenUtil.getScreenWidth(itemView.getContext());
        int dp15 = ScreenUtil.dip2px(itemView.getContext(),15);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((width - dp15) / 2,(width-dp15)/2 * 86 / 173);
        like_qa_img1.setLayoutParams(layoutParams);
    }

}