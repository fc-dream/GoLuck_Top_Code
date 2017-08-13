package top.goluck.recyclerview_2017_8_13

import android.graphics.Canvas
import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View

/**
 * Created by luck on 2017/8/13.
 */
class BackgroundDecoration : RecyclerView.ItemDecoration() {

    private var leftPaint = 10
    private var tagWidth = 20
    private var rightPaint = 10

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        var childCount = parent.getChildCount()
        for (i in 0..childCount) {
            var child = parent.getChildAt(i)
            var pos = parent.getChildAdapterPosition(child)
            var isLeft = pos % 2 == 0
            if (isLeft) {
                var left = child.getLeft()
                var right = left + tagWidth
                var top = child.getTop()
                var bottom = child.getBottom()
                c.drawRect(left, top, right, bottom, leftPaint)
            } else {
                var right = child.getRight()
                var left = right - tagWidth
                var top = child.getTop()
                var bottom = child.getBottom()
                c.drawRect(left, top, right, bottom, rightPaint)

            }
        }
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

//        val itemCount = state.getItemCount()
//        val childCount = parent.getChildCount()
//        val firstVisiblePos = (parent.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
//
//        Log.i("tag","-----------------------itemCount = $itemCount")
//        Log.i("tag","-----------------------childCount = $childCount")
//        Log.i("tag","-----------------------firstVisiblePos = $firstVisiblePos")
//
//        outRect!!.top = 10 //类似加了一个top padding 10px
//        outRect.left = 10 //类似加了一个left padding 10px
//        outRect.right = 10 //类似加了一个right padding 10px
//
//        if((itemCount==firstVisiblePos+childCount))
//            outRect.bottom = 10 //类似加了一个bottom padding 10px
    }
}