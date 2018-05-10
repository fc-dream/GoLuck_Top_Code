package cn.tuhu.home.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * 作者：luck on 2018/4/26 16:32
 * 邮箱：fc_dream@163.com
 * Tuhu_Android
 */
public class RobotoTextView extends android.support.v7.widget.AppCompatTextView {

    public RobotoTextView(Context context) {
        super(context);
        init(context);
    }

    public RobotoTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RobotoTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //加载字体文件
        Typeface typeface = IconFontTypeFace.getTypeface(context);
        this.setTypeface(typeface);
    }

    public static class IconFontTypeFace {
        private static Typeface ttfTypeface = null;

        public static synchronized Typeface getTypeface(Context context) {
            if (ttfTypeface == null) {
                try {
                    ttfTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return ttfTypeface;
        }
    }


}
