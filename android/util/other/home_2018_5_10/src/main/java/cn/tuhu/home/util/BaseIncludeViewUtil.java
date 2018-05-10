package cn.tuhu.home.util;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

/**
 * 作者：luck on 2017/3/3 10:51
 * 邮箱：fc_dream@163.com
 * Tuhu_Android
 */
public abstract class BaseIncludeViewUtil {

    public View mView;
    private Activity mActivity;

    public BaseIncludeViewUtil(Activity activity, View view) {
        this.mActivity = activity;
        this.mView = view;
    }

    public Activity getActivity() {
        return mActivity;
    }

    protected <T extends View> T getView(int id) {
        if (mView == null) {
            return null;
        }
        return (T) (mView.findViewById(id));
    }

    protected void setVisibility(boolean isVisible) {
        if (mView == null) {
            return;
        }
        mView.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    protected void loadImage(String url, ImageView imageView) {
//        loadImage(url, imageView, 0, 0, null);
    }

//    protected void loadImage(String url, ImageView imageView, IgetImageView igetImageView) {
//        loadImage(url, imageView, 0, 0, igetImageView);
//    }
//
//    protected void loadImage(String url, ImageView imageView, int w, int h, IgetImageView igetImageView) {
//        if (imageView == null || TextUtils.isEmpty(url)) {
//            return;
//        }
//        if (w == 0 || h == 0) {
//            ImageLoaderUtil.init(getActivity()).setFullSize(true).setmIgetImageView(igetImageView).with(url, imageView);
//            return;
//        }
//        ImageLoaderUtil.init(getActivity())
//                .setFullSize(true)
//                .setmIgetImageView(igetImageView)
//                .with(url, imageView, w, h);
//    }
}
