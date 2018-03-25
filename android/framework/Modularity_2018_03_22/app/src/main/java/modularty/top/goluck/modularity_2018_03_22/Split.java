package modularty.top.goluck.modularity_2018_03_22;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：luck on 2016/11/2 17:40
 * 邮箱：fc_dream@163.com
 * SplitView
 */
public class Split {

    public final static int STYLE_2_2 = 1;
    public final static int STYLE_3_2 = 2;
    public final static int STYLE_3_3 = 3;
    public final static int STYLE_Other = 4;
    private int check_STYLE = 1;
    private int position = -1;
    private int width = -1, height = -1;
    private List<Rect> mRects = new ArrayList<>();
    private List<FRect> mFRects;
    private float mTouchX;
    private float mTouchY;

    /**
     * 当前选择的位置 从0开始
     *
     * @return
     */
    protected int getPosition() {
        return this.position;
    }

    protected void onLayout(int w, int h) {
        if (width != w || height != h) {
            width = w;
            height = h;
            setStyle(check_STYLE);
        }
    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(
                    attrs,
                    R.styleable.Split,
                    defStyleAttr,
                    0);
            check_STYLE = ta.getInt(R.styleable.Split_style, 1);
            ta.recycle();
        }
    }


    protected void setStyle(int style) {
        initValues();
        switch (style) {
            case STYLE_2_2:
                check_STYLE = STYLE_2_2;
                two_Two();
                break;
            case STYLE_3_2:
                check_STYLE = STYLE_3_2;
                three_Two();
                break;
            case STYLE_3_3:
                check_STYLE = STYLE_3_3;
                three_Three();
                break;
            case STYLE_Other:
                check_STYLE = STYLE_Other;
                other();
                break;
            default:
                check_STYLE = STYLE_2_2;
                two_Two();
                break;
        }
    }

    protected void setOtherStyle(List<FRect> fRects) {
        if (fRects != null) {
            this.mFRects = fRects;
        } else {
            this.mFRects = null;
        }
        setStyle(STYLE_Other);
    }


    protected void onDraw(Canvas canvas) {
    }

    private boolean isclick = false;//是否正在执行点击事件
    protected void onTouchEvent(MotionEvent event, View view) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(isclick){
                    return;
                }
                isclick = true;
                mTouchX = event.getX();
                mTouchY = event.getY();
                if (mRects != null) {
                    boolean ischeckposition = false;
                    for (int i = 0; i < mRects.size(); i++) {
                        Rect myRect = mRects.get(i);
                        if (myRect.contains((int) mTouchX, (int) mTouchY)) {
                            ischeckposition = true;
                            position = i;
                            view.invalidate();
                            break;
                        }
                    }
                    if(!ischeckposition){
                        position = -1;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                isclick = false;
                break;
        }
    }

    private void initValues() {
        this.position = -1;
        this.check_STYLE = 1;
    }

    private void two_Two() {

        Rect myRect1 = new Rect(0, 0, width * 1 / 2, height * 1 / 2);
        Rect myRect2 = new Rect(width * 1 / 2, 0, width, height * 1 / 2);

        Rect myRect3 = new Rect(0, height * 1 / 2, width * 1 / 2, height);
        Rect myRect4 = new Rect(width * 1 / 2, height * 1 / 2, width, height);

        mRects.clear();
        mRects.add(myRect1);
        mRects.add(myRect2);
        mRects.add(myRect3);
        mRects.add(myRect4);
    }

    private void three_Two() {

        Rect myRect1 = new Rect(0, 0, width * 1 / 3, height * 1 / 2);
        Rect myRect2 = new Rect(width * 1 / 3, 0, width * 2 / 3, height * 1 / 2);
        Rect myRect3 = new Rect(width * 2 / 3, 0, width, height * 1 / 2);

        Rect myRect4 = new Rect(0, height * 1 / 2, width * 1 / 3, height);
        Rect myRect5 = new Rect(width * 1 / 3, height * 1 / 2, width * 2 / 3, height);
        Rect myRect6 = new Rect(width * 2 / 3, height * 1 / 2, width, height);

        mRects.clear();
        mRects.add(myRect1);
        mRects.add(myRect2);
        mRects.add(myRect3);
        mRects.add(myRect4);
        mRects.add(myRect5);
        mRects.add(myRect6);
    }

    private void three_Three() {

        Rect myRect1 = new Rect(0, 0, width * 1 / 3, height * 1 / 3);
        Rect myRect2 = new Rect(width * 1 / 3, 0, width * 2 / 3, height * 1 / 3);
        Rect myRect3 = new Rect(width * 2 / 3, 0, width, height * 1 / 3);

        Rect myRect4 = new Rect(0, height * 1 / 3, width * 1 / 3, height * 2 / 3);
        Rect myRect5 = new Rect(width * 1 / 3, height * 1 / 3, width * 2 / 3, height * 2 / 3);
        Rect myRect6 = new Rect(width * 2 / 3, height * 1 / 3, width, height * 2 / 3);

        Rect myRect7 = new Rect(0, height * 2 / 3, width * 1 / 3, height);
        Rect myRect8 = new Rect(width * 1 / 3, height * 2 / 3, width * 2 / 3, height);
        Rect myRect9 = new Rect(width * 2 / 3, height * 2 / 3, width, height);

        mRects.clear();
        mRects.add(myRect1);
        mRects.add(myRect2);
        mRects.add(myRect3);
        mRects.add(myRect4);
        mRects.add(myRect5);
        mRects.add(myRect6);
        mRects.add(myRect7);
        mRects.add(myRect8);
        mRects.add(myRect9);
    }

    private void other() {
        mRects.clear();
        if (mFRects == null) {
            return;
        }
        for (int i = 0; i < mFRects.size(); i++) {
            FRect mFRect = mFRects.get(i);
            Rect mRect = new Rect((int) (width * mFRect.left), (int) (height * mFRect.top), (int) (width * mFRect.right), (int) (height * mFRect.bottom));
            mRects.add(mRect);
        }
    }

    public interface onSplitClickListener {
        void onSplitClick(View v, int position);
    }
}
