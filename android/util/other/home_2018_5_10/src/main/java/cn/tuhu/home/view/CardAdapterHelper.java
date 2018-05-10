package cn.tuhu.home.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * adapter中调用onCreateViewHolder, onBindViewHolder
 * Created by jameson on 9/1/16.
 */
public class CardAdapterHelper {

    public static int mPagePadding = 10;

    public void onCreateViewHolder(ViewGroup parent,  View itemView) {
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        lp.width = ScreenUtil.getScreenWidth(parent.getContext()) - ScreenUtil.dip2px(itemView.getContext(), 3*mPagePadding);
        itemView.setLayoutParams(lp);
        itemView.requestLayout();
    }

    public void onBindViewHolder(View itemView) {
        int padding = ScreenUtil.dip2px(itemView.getContext(), mPagePadding);
        itemView.setPadding(padding, 0, 0, 0);
        itemView.requestLayout();
    }

    public void setPagePadding(int pagePadding) {
        mPagePadding = pagePadding;
    }

}
