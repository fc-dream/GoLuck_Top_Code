package top.goluck.recyclerview_2017_8_13.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ItemDecoration
import android.view.View


/**
 * Created by luck on 2017/8/13.
 */
class LineDecoration() : ItemDecoration() {

    private var dividerHeight = 10
    private lateinit var dividerPaint: Paint

    constructor(context: Context, dividerheight: Int) : this() {
        this.dividerHeight = dividerheight
        dividerPaint = Paint()
        dividerPaint.color = context.resources.getColor(android.R.color.white)
    }

    override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDrawOver(c, parent, state)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
        var childCount = parent.childCount

        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 0..childCount) {
            var child: View = parent.getChildAt(i) ?: continue
            var top = child.bottom
            var bottom = top + dividerHeight;
            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), dividerPaint)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = dividerHeight
    }
}