package cn.tuhu.home.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by hiwhitley on 2016/9/4.
 */
public class CardScaleLeftSnapHelper extends LinearSnapHelper {

    private OrientationHelper mHorizontalHelper;

    public boolean mNoNeedToScroll = false;

    public int[] finalSnapDistance = {0, 0};
//    @Override
//    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
//        //Log.e("TAG", "calculateDistanceToFinalSnap");
//        if (mNoNeedToScroll) {
//            finalSnapDistance[0] = 0;
//            finalSnapDistance[1] = 0;
//        } else {
//            finalSnapDistance = super.calculateDistanceToFinalSnap(layoutManager, targetView);
//        }
//        return finalSnapDistance;
//    }

    @Nullable
    @Override
    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View targetView) {
        if (mNoNeedToScroll) {
            finalSnapDistance[0] = 0;
            finalSnapDistance[1] = 0;
        }else {
            if (layoutManager.canScrollHorizontally()) {
                finalSnapDistance[0] = distanceToStart(targetView, getHorizontalHelper(layoutManager));
            } else {
                finalSnapDistance[0] = 0;
            }
        }

        return finalSnapDistance;
    }

    private int distanceToStart(View targetView, OrientationHelper helper) {
        return helper.getDecoratedStart(targetView) - helper.getStartAfterPadding();
    }

    @Nullable
    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        return findStartView(layoutManager, getHorizontalHelper(layoutManager));
    }

    private View findStartView(RecyclerView.LayoutManager layoutManager,
                               OrientationHelper helper) {

        if (layoutManager instanceof LinearLayoutManager) {
            int firstChild = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            int lastChild = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            if (firstChild == RecyclerView.NO_POSITION) {
                return null;
            }
            if (lastChild == layoutManager.getItemCount() - 1) {
                return layoutManager.findViewByPosition(lastChild);
            }

            View child = layoutManager.findViewByPosition(firstChild);

            if (helper.getDecoratedEnd(child) >= helper.getDecoratedMeasurement(child) / 2
                    && helper.getDecoratedEnd(child) > 0) {
                return child;
            } else {
                return layoutManager.findViewByPosition(firstChild + 1);
            }
        }

        return super.findSnapView(layoutManager);
    }


    private OrientationHelper getHorizontalHelper(
            @NonNull RecyclerView.LayoutManager layoutManager) {
        if (mHorizontalHelper == null) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return mHorizontalHelper;
    }
}