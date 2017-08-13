package top.goluck.recyclerview_2017_8_13.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import top.goluck.recyclerview_2017_8_13.R

/**
 * Created by luck on 2017/8/13.
 */
class BackgroundDecoration() : RecyclerView.ItemDecoration() {

    private lateinit var context: Context
    private lateinit var leftPaint: Paint
    private lateinit var rightPaint: Paint
    private var tagWidth = 20

    constructor(context: Context) : this() {
        this.context = context
        leftPaint = Paint()
        leftPaint.color = context.resources.getColor(R.color.colorAccent)
        rightPaint = Paint()
        rightPaint.color = context.resources.getColor(android.R.color.white)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        var childCount = parent.childCount
        for (i in 0..childCount) {
            var child: View = parent.getChildAt(i) ?: continue
            var pos = parent.getChildAdapterPosition(child)
            var isLeft = pos % 2 == 0
            if (isLeft) {
                var left = child.left
                var right = left + tagWidth
                var top = child.top
                var bottom = child.bottom
                c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), leftPaint)
            } else {
                var right = child.right
                var left = right - tagWidth
                var top = child.top
                var bottom = child.bottom
                c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), rightPaint)

            }
        }
    }

    //绘制背景
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        var childCount = parent.childCount
        for (i in 0..childCount) {
            var child: View = parent.getChildAt(i) ?: continue
            var pos = parent.getChildAdapterPosition(child)
            var isLeft = pos % 2 == 1
            if (isLeft) {
                var left = child.left
                var right = left + tagWidth
                var top = child.top
                var bottom = child.bottom
                c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), leftPaint)
            } else {
                var right = child.right
                var left = right - tagWidth
                var top = child.top
                var bottom = child.bottom
                c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), rightPaint)

            }
        }
    }

    //留padding top1 左tagWidth  top2右tagWidth
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val childCount = parent.childCount
        var firstVisiblePos = (parent.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        if(firstVisiblePos==-1)
            firstVisiblePos = 0
        if ((firstVisiblePos + childCount) % 2 == 1) {
            outRect.left = tagWidth
            outRect.right = 0
        }
        else {
            outRect.right = tagWidth
            outRect.left = 0
        }
    }
}