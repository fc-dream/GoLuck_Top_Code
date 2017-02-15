package top.goluck.realm_2017_2_8.base.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 作者：luck on 2016/1/17 17:00
 * 邮箱：fc_dream@163.com
 * AS_TuHu_android
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    protected Context context;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.context = itemView.getContext();
    }

    /**
     * 得到当前view绑定的activity
     * @return
     */
    protected Activity getActivity(){
        return (Activity) context;
    }

    /**
     * itemView的子view 的 findViewById
     * @param viewId id
     * @return
     */
    protected <T extends View> T getView(int viewId) {
        return (T) (itemView.findViewById(viewId));
    }

    /**
     * 非itemView 的 findViewById
     * @param view 需要findViewById的父View
     * @param viewId id
     * @param <T> 指定要转换的view类型
     * @return 返回View
     */
    protected <T extends View> T getView(View view, int viewId) {
        if(view==null){
            return null;
        }
        return (T) (view.findViewById(viewId));
    }

    protected boolean isVisble = true;
    public void setVisibility(boolean isVisible){
        RecyclerView.LayoutParams param = (RecyclerView.LayoutParams)itemView.getLayoutParams();
        if (isVisible){
            param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            param.width = LinearLayout.LayoutParams.MATCH_PARENT;
            itemView.setVisibility(View.VISIBLE);
        }else{
            itemView.setVisibility(View.GONE);
            param.height = 0;
            param.width = 0;
        }
        this.isVisble = isVisible;
        itemView.setLayoutParams(param);
    }

}
