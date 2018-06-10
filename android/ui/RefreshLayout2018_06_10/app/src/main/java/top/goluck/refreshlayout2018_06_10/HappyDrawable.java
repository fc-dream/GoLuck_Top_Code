package top.goluck.refreshlayout2018_06_10;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * @author ：luck on 2018/6/8 17:53
 * @email ：fc_dream@163.com
 * SmartRefreshLayout
 */
public class HappyDrawable extends View implements RefreshHeader {

    public HappyDrawable(Context context) {
        super(context);
        init(context);
    }

    public HappyDrawable(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HappyDrawable(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HappyDrawable(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private Happy mHappy;
    private boolean isRuning = false;
    private boolean isShow = false;

    public void setRuning(boolean isRuning) {
        this.isRuning = isRuning;
        invalidate();
    }

    private void init(Context context) {
        mHappy = new Happy(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isShow) {
            int viewWidth = getWidth();
            int viewHeight = getHeight();

            drawBg(canvas, viewWidth);
            drawHappy(canvas, viewWidth, viewHeight);
            Log.i("tag","------------------------------------onDraw");
            postInvalidateDelayed(150);
        }
    }

    // 背景总共移动的位置
    private int nowX=0;
    // 背景每次移动的距离
    private int speed=5;
    private void drawBg(Canvas canvas, int viewWidth) {
        int imgW = mHappy.happy_bg_width;
        if (isRuning) {
            if (-nowX < imgW) {
                nowX -= speed;
            } else {
                nowX = 0;
            }
        }
        int index = 0;
        canvas.translate(0, 0);
        while (index * imgW + nowX < viewWidth) {
            canvas.drawBitmap(mHappy.happy_bg, index * imgW + nowX, 0, null);
            index++;
        }
    }

    private int index = 0;

    /**
     * 画移动的Happy僵尸
     *
     * @param canvas
     * @param viewWidth
     */
    private void drawHappy(Canvas canvas, int viewWidth, int viewHeight) {

        int imgW = mHappy.happy_width;
        int imgH = mHappy.happy_height;

        int row = index < 11 ? 0 : 1;
        // 移动x,y的坐标
        int left = row == 0 ? index * imgW : (index - 11) * imgW;
        int top = row * imgH;

        // 将画布坐标系移动到画布中央
        canvas.translate(viewWidth / 2 - mHappy.happy_width / 2, viewHeight / 2 - mHappy.happy_height / 2);

        // 指定图片绘制区域的22分之一
        Rect src = new Rect(left, top, left + mHappy.happy_width, top + mHappy.happy_height);

        // 指定图片在屏幕上显示的区域
        Rect dst = new Rect(0, 0, mHappy.happy_width, mHappy.happy_height);

        // 绘制图片
        canvas.drawBitmap(mHappy.happy, src, dst, null);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(25f);
        // 绘制文字
        canvas.drawText("当前绘制在第"+index +"帧数",viewWidth / 2 -200,30,paint);

        if (isRuning) {
            // 绘制的次数
            index++;
            if (index == 22) {
                index = 0;
            }
        } else {
            index = 0;
        }
        Log.i("tag","------------------------------------index="+index);
    }

    /**
     * 手指释放之后的持续动画
     *
     * @param percent      下拉的百分比 值 = offset/headerHeight (0 - percent - (headerHeight+extendHeight) / headerHeight )
     * @param offset       下拉的像素偏移量  0 - offset - (headerHeight+extendHeight)
     * @param headerHeight Header的高度
     * @param extendHeight Header的扩展高度
     */
    @Override
    public void onReleasing(float percent, int offset, int headerHeight, int extendHeight) {
    }

    /**
     * 释放时刻（调用一次，将会触发加载）
     *
     * @param refreshLayout RefreshLayout
     * @param height        高度 HeaderHeight or FooterHeight
     * @param extendHeight  扩展高度  extendHeaderHeight or extendFooterHeight
     */
    @Override
    public void onReleased(RefreshLayout refreshLayout, int height, int extendHeight) {

    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    /**
     * 顶部和底部的组件在拖动时候的变换方式，这里用平移模式
     */
    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    /**
     * 暂不用 颜色
     */
    @Override
    public void setPrimaryColors(int... colors) {
    }

    /**
     * 暂不用 尺寸定义完成 （如果高度不改变（代码修改：setHeader），只调用一次, 在RefreshLayout#onMeasure中调用）
     */
    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int extendHeight) {
    }

    /**
     * 手指拖动下拉（会连续多次调用）
     *
     * @param percent      下拉的百分比 值 = offset/footerHeight (0 - percent - (footerHeight+extendHeight) / footerHeight )
     * @param offset       下拉的像素偏移量  0 - offset - (footerHeight+extendHeight)
     * @param height       高度 HeaderHeight or FooterHeight
     * @param extendHeight 扩展高度  extendHeaderHeight or extendFooterHeight
     */
    @Override
    public void onPulling(float percent, int offset, int height, int extendHeight) {

    }

    /**
     * 水平方向的拖动
     */
    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
    }

    /**
     * 开始动画
     */
    @Override
    public void onStartAnimator(@NonNull RefreshLayout layout, int height, int extendHeight) {
    }

    /**
     * 动画结束
     *
     * @param layout  RefreshLayout
     * @param success 数据是否成功刷新或加载
     * @return 完成动画所需时间 如果返回 Integer.MAX_VALUE 将取消本次完成事件，继续保持原有状态
     */
    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        return 0;
    }

    /**
     * 是否支持水平方向的拖动（将会影响到onHorizontalDrag的调用）
     *
     * @return 水平拖动需要消耗更多的时间和资源，所以如果不支持请返回false
     */
    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    /**
     * 状态改变事件 {@link RefreshState}
     *
     * @param refreshLayout RefreshLayout
     * @param oldState      改变之前的状态
     * @param newState      改变之后的状态
     */
    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState) {
            //开始往下拉
            case PullDownToRefresh:
                begin();
                break;
            //正在刷新
            case Refreshing:
                start();
                break;
            //刷新完成
            case RefreshFinish:
//                stop();
                break;
        }
    }

    /**
     * 下拉准备中，显示第一帧
     */
    private void begin() {
        isShow = true;
        invalidate();
        Log.i("tag","------------------------------------begin");
    }

    /**
     * 开始动画
     */
    private void start() {
        setRuning(true);
        invalidate();
        Log.i("tag","------------------------------------start");
    }

    /**
     * 结束动画
     */
    private void stop() {
        isShow = false;
        setRuning(false);
        invalidate();
        Log.i("tag","------------------------------------stop");
    }

}
