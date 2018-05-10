package cn.tuhu.home.view;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制fling速度的RecyclerView
 *
 * Created by jameson on 9/1/16.
 */
public class SpeedRecyclerView extends RecyclerView {
    private static final float FLING_SCALE_DOWN_FACTOR = 0.5f; // 减速因子
    private static final int FLING_MAX_VELOCITY = 8000; // 最大顺时滑动速度
    private static boolean mEnableLimitVelocity = true; // 最大顺时滑动速度
    private List<OnPageChangeListener> mOnPageChangeListeners;
    private OnPageChangeListener mOnPageChangeListener;

    public SpeedRecyclerView(Context context) {
        super(context);
    }

    public SpeedRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SpeedRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public static boolean ismEnableLimitVelocity() {
        return mEnableLimitVelocity;
    }

    public static void setmEnableLimitVelocity(boolean mEnableLimitVelocity) {
        SpeedRecyclerView.mEnableLimitVelocity = mEnableLimitVelocity;
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        if (mEnableLimitVelocity) {
            velocityX = solveVelocity(velocityX);
            velocityY = solveVelocity(velocityY);
        }
        return super.fling(velocityX, velocityY);
    }

    private int solveVelocity(int velocity) {
        if (velocity > 0) {
            return Math.min(velocity, FLING_MAX_VELOCITY);
        } else {
            return Math.max(velocity, -FLING_MAX_VELOCITY);
        }
    }

    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mOnPageChangeListener = listener;
    }

    public void addOnPageChangeListener(OnPageChangeListener listener) {
        if (mOnPageChangeListeners == null) {
            mOnPageChangeListeners = new ArrayList<>();
        }
        mOnPageChangeListeners.add(listener);
    }

    public void removeOnPageChangeListener(OnPageChangeListener listener) {
        if (mOnPageChangeListeners != null) {
            mOnPageChangeListeners.remove(listener);
        }
    }

    public void clearOnPageChangeListeners() {
        if (mOnPageChangeListeners != null) {
            mOnPageChangeListeners.clear();
        }
    }


    public interface OnPageChangeListener {
        void onPageSelected(int position);
    }

    private int position;
    public void dispatchOnPageSelected(int position) {
        this.position = position;
        if (isTouched) {
            isTouched = false;
            setPlaying(true);
        }
        if(isAutoPlaying && !isPlaying){
            setPlaying(true);
        }
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageSelected(position);
        }
        if (mOnPageChangeListeners != null) {
            for (int i = 0, z = mOnPageChangeListeners.size(); i < z; i++) {
                OnPageChangeListener listener = mOnPageChangeListeners.get(i);
                if (listener != null) {
                    listener.onPageSelected(position);
                }
            }
        }
    }

    private int startX, startY;
    private boolean isTouched;
    private boolean isPlaying;
    private int mInterval = 3000;
    private boolean isAutoPlaying = true;

    /**
     * 设置是否支持自动轮播
     * @param autoPlaying 是否自动轮播
     */
    public void setAutoPlaying(boolean autoPlaying) {
        isAutoPlaying = autoPlaying;
    }

    /**
     * 设置轮播间隔时间
     * @param millisecond 时间毫秒
     */
    public void setIndicatorInterval(int millisecond) {
        this.mInterval = millisecond;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //手动触摸的时候，停止自动播放，根据手势变换
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getX();
                int moveY = (int) ev.getY();
                int disX = moveX - startX;
                int disY = moveY - startY;
                boolean hasMoved = 2 * Math.abs(disX) > Math.abs(disY);
                getParent().requestDisallowInterceptTouchEvent(hasMoved);
                if (hasMoved) {
                    setPlaying(false);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (!isPlaying) {
                    isTouched = true;
                    setPlaying(true);
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private Handler handler = new Handler();

    private Runnable playTask = new Runnable() {

        @Override
        public void run() {
            smoothScrollToPosition(++position);
            handler.postDelayed(this, mInterval);
        }
    };

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setPlaying(true);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setPlaying(false);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        if (visibility == GONE || visibility == INVISIBLE) {
            // 停止轮播
            setPlaying(false);
        } else if (visibility == VISIBLE) {
            // 开始轮播
            setPlaying(true);
        }
        super.onWindowVisibilityChanged(visibility);
    }

    /**
     * 设置是否自动播放（上锁）
     *
     * @param playing 开始播放
     */
    private synchronized void setPlaying(boolean playing) {
        if (isAutoPlaying) {
            if (!isPlaying && playing && this.getAdapter() != null && this.getAdapter().getItemCount() > 2) {
                handler.postDelayed(playTask, mInterval);
                isPlaying = true;
            } else if (isPlaying && !playing) {
                handler.removeCallbacksAndMessages(null);
                isPlaying = false;
            }
        }
    }

}
