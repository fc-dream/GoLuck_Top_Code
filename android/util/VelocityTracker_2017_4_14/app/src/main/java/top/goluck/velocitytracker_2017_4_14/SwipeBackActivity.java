package top.goluck.velocitytracker_2017_4_14;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;

/**
 * 作者：luck on 2017/4/14 11:17
 * 邮箱：fc_dream@163.com
 * VelocityTracker_2017_4_14
 *
 * 继承之后的Activity背景默认是透明，需要继承activity重新设置背景色。
 *
 */
public class SwipeBackActivity extends AppCompatActivity {

    private int mTouchDistance;//屏幕距离左边缘 可触发滑动的距离
    private View mDecorView;//当前界面的容器
    private int mDecorViewWidth;//当前界面的宽度
    private int startX, startY;//按下时的x y 坐标
    private int DURATION = 300;//动画间隔时间
    private ValueAnimator mAnimator;//绘制滑动动画
    private int mTouchSlop;//认为用户在滚动的一个值
    private boolean mIsMoving;//当前滑动状态
    private ArgbEvaluator mEvaluator;//颜色渐变处理器

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.xml_seat_shape);
        mEvaluator = new ArgbEvaluator();
        mDecorView = getWindow().getDecorView();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mDecorViewWidth =  dm.widthPixels;//getResources().getDisplayMetrics().widthPixels;
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        mTouchDistance = mDecorViewWidth / 30;
        mAnimator = new ValueAnimator();
        mAnimator.setDuration(DURATION);
        mAnimator.setInterpolator(new DecelerateInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int x = (Integer) valueAnimator.getAnimatedValue();
                if (x >= mDecorViewWidth) {
                    onBackPressed();
                }
                handleView(x);
                handleBackgroundColor(x);
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mAnimator.isRunning()) {
            return true;
        }
        addMovement(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getRawX();
                startY = (int) ev.getRawY();
                if (startX < mTouchDistance) {//满足触发左右滑动
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(startX < mTouchDistance) {//满足触发左右滑动条件
                    if (!mIsMoving) {
                        float dx = Math.abs(ev.getRawX() - startX);//移动的x距离
                        float dy = Math.abs(ev.getRawY() - startY);//移动的y距离
//                        if(dx > mTouchSlop){//用户滑动是否有滚动的行为
//                            mIsMoving = true;
//                        }
                        if (dx > dy ) {//1，只有x滑动距离大于y滑动距离，才满足处理条件
                            mIsMoving = true;
                        }
                    }
                    if (mIsMoving) {
                        handleView((int) ev.getRawX());
                        handleBackgroundColor(ev.getRawX());
                        return true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if(mIsMoving) {
                    int distance = (int) (ev.getRawX() - startX);
                    mVelTracker.computeCurrentVelocity(1000);
                    //获取x方向上的速度
                    float velocityX = mVelTracker.getXVelocity();
                    Log.i("tag", "-----------------------------velocityX=" + velocityX);
                    if (mIsMoving && Math.abs(mDecorView.getScrollX()) >= 0) {//mDecorView.getTranslationX()
                        if (velocityX > 1000f || distance >= mDecorViewWidth / 2) {
                            mAnimator.setIntValues((int) ev.getRawX(), mDecorViewWidth);
                        } else {
                            mAnimator.setIntValues((int) ev.getRawX(), 0);
                        }
                        mAnimator.start();
                        mIsMoving = false;
                    }
                    startX = 0;
                    startY = 0;
                }
                recycleVelocityTracker();
                break;
        }

        return super.dispatchTouchEvent(ev);
    }
    /**
     * 控制activity 移动
     * @param x
     */
    public void handleView(int x) {
//      mDecorView.setTranslationX(x);
        mDecorView.scrollTo(-x, 0);
    }

    /**
     * 控制背景颜色和透明度
     * @param x
     */
    private void handleBackgroundColor(float x) {
        int colorValue = (int) mEvaluator.evaluate(x / mDecorViewWidth,
                Color.parseColor("#dd000000"), Color.parseColor("#00000000"));
//      mDecorView.setBackgroundColor(colorValue);
        mDecorView.getBackground().setColorFilter(colorValue, PorterDuff.Mode.SRC_OVER);
    }

    private VelocityTracker mVelTracker;

    /**
     * 获取速度追踪器
     *
     * @return
     */
    private void addMovement(MotionEvent event) {
        if (mVelTracker == null) {
            mVelTracker = VelocityTracker.obtain();
        }
        mVelTracker.addMovement(event);
    }

    /**
     * 回收速度追踪器
     */
    private void recycleVelocityTracker() {
        if (mVelTracker != null) {
            mVelTracker.clear();
            mVelTracker.recycle();
            mVelTracker = null;
        }
    }
}
