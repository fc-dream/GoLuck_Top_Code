package modularty.top.goluck.modularity_2018_03_22;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;

/**
 * 作者：luck on 2016/10/27 19:29
 * 邮箱：fc_dream@163.com
 * spliteview
 */
public class SplitImageView extends LottieAnimationView {

    private Split mSplit;
    private Split.onSplitClickListener mOnSplitClickListener;

    public SplitImageView(Context context) {
        super(context,null);
        init(context, null, 0);
    }

    public SplitImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public SplitImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SplitImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int w = getWidth();
        int h = getHeight();
        initView();
        mSplit.onLayout(w, h);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        setClickable(true);
        mSplit = new Split();
        mSplit.init(context, attrs, defStyleAttr);
    }

    private boolean isclicking=false;//防止连续点击
    private void initView() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnSplitClickListener != null) {
                    if(mSplit.getPosition()!=-1){
                        if(isclicking){
                            return;
                        }
                    }
                    isclicking = true;
                    mOnSplitClickListener.onSplitClick(v, mSplit.getPosition());
                    isclicking = false;
                }
            }
        });
    }

    public void setStyle(int style) {
        mSplit.setStyle(style);
    }

    public void setOtherStyle(List<FRect> fRects) {
        mSplit.setOtherStyle(fRects);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mSplit.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mSplit.onTouchEvent(event, this);
        return super.onTouchEvent(event);
    }

    public void setOnSplitClickListener(Split.onSplitClickListener onSplitClickListener) {
        this.mOnSplitClickListener = onSplitClickListener;
    }
}
