package top.goluck.recyclerview_2017_8_13

import android.graphics.Canvas
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by 员工 on 2017/8/13.
 */
class PaddingDecoration: RecyclerView.ItemDecoration() {

    override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDrawOver(c, parent, state)
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect!!.bottom = 10 //类似加了一个bottom padding 10px
        outRect.top = 10 //类似加了一个top padding 10px
        outRect.left = 10 //类似加了一个left padding 10px
        outRect.right = 10 //类似加了一个right padding 10px
    }
}