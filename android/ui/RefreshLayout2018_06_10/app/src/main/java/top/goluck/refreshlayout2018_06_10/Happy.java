package top.goluck.refreshlayout2018_06_10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author ：luck on 2018/6/8 18:14
 * @email ：fc_dream@163.com
 * SmartRefreshLayout
 */
public class Happy {
    //happy僵尸大图
    public Bitmap happy;
    public Bitmap happy_bg;
    //背景图片宽度高度
    public int happy_bg_width;
    public int happy_bg_height;
    //图片宽度高度 （每帧）
    public int happy_width;
    public int happy_height;

    public Happy(Context context) {

        happy_bg = BitmapFactory.decodeResource(context.getResources(), R.drawable.shi);
        happy_bg_width = happy_bg.getWidth();
        happy_bg_height = happy_bg.getHeight();

        happy = BitmapFactory.decodeResource(context.getResources(), R.drawable.happy);
        happy_width = happy.getWidth() / 11;
        happy_height = happy.getHeight() / 2;
    }

}
