package top.goluck.recyclerview_2017_8_13.decoration

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import top.goluck.recyclerview_2017_8_13.R
import android.app.Activity
import android.view.ViewGroup
import android.text.TextUtils
import android.widget.TextView
import top.goluck.recyclerview_2017_8_13.titles


/**
 * Created by luck on 2017/8/13.
 */
class CustomViewDecoration() : RecyclerView.ItemDecoration() {

    private lateinit var context: Activity
    private var mGroupHeight = 120
    private var mDivideHeight = 10
    var isAlignLeft = true //是否靠左边
    private lateinit var mGroutPaint: Paint
    private lateinit var mDividePaint: Paint

    constructor(context: Activity) : this() {
        this.context = context
        this.mGroutPaint = Paint()
        mGroutPaint.color = context.resources.getColor(android.R.color.transparent)
        mDividePaint = Paint()
        mDividePaint.color = context.resources.getColor(R.color.colorAccent)
    }


    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val itemCount = state.itemCount
        val childCount = parent.childCount
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        var preGroupName: String? = null
        var currentGroupName: String? = null
        for (i in 0..childCount) {
            var view = parent.getChildAt(i) ?: continue
            var position = parent.getChildAdapterPosition(view)
            if(position<0){
                position = 0
            }
            preGroupName = currentGroupName
            currentGroupName = getGroupName(position)
            if (currentGroupName == null || TextUtils.equals(currentGroupName, preGroupName)) {
                //绘制分割线
                if (mGroupHeight != 0) {
                    var bottom = view.top
                    if (bottom < mGroupHeight) {
                        //高度小于顶部悬浮栏时，跳过绘制
                        continue
                    }
                    c.drawRect(left.toFloat(), (bottom - mDivideHeight).toFloat(), right.toFloat(), bottom.toFloat(), mDividePaint)
                }
            } else {
                var viewBottom = view.bottom
                var top = Math.max(mGroupHeight, view.top)//top 决定当前顶部第一个悬浮Group的位置
                if (position + 1 < itemCount) {
                    //获取下个GroupName
                    var nextGroupName = getGroupName (position + 1)
                    //下一组的第一个View接近头部
                    if (!currentGroupName.equals(nextGroupName) && viewBottom < top) {
                        top = viewBottom;
                    }
                }
                c.drawRect(left.toFloat(), (top - mGroupHeight).toFloat(), right.toFloat(), top.toFloat(), mGroutPaint)
                //根据position获取View
                val groupView = getGroupView(position) ?: return
                val layoutParams = ViewGroup.LayoutParams(right, mGroupHeight)
                groupView.layoutParams = layoutParams
                groupView.isDrawingCacheEnabled = true
                groupView.measure(
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
                //指定高度、宽度的groupView
                groupView.layout(0, 0, right, mGroupHeight)
                groupView.buildDrawingCache()
                val bitmap = groupView.drawingCache
                val marginLeft = if (isAlignLeft) 0 else right - groupView.measuredWidth
                c.drawBitmap(bitmap, (left + marginLeft).toFloat(), (top - mGroupHeight).toFloat(), Paint())
            }
        }
    }

    private fun getGroupView(position: Int): View? {
        val view = context.getLayoutInflater().inflate(R.layout.coustomeview, null, false)
        view.findViewById<TextView>(R.id.tv_title).text = titles.get(position / 10)
        return view
    }

    private fun getGroupName(position: Int): String {
        return titles.get(position / 10)
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val pos = parent.getChildAdapterPosition(view)
        //只有是同一组的第一个才显示悬浮栏
        if (pos == 0 || isFirstInGroup(pos)) {
            outRect.top = mGroupHeight //为悬浮view预留空间
        } else {
            outRect.top = mDivideHeight //为分割线预留空间
        }
    }

    /**
     * 判断是不是组中的第一个位置
     * 根据前一个组名，判断当前是否为新的组
     */
    private fun isFirstInGroup(pos: Int): Boolean {
        if (pos == 0) {
            return true
        } else {
            val prevGroupId = getGroupName(pos - 1)
            val groupId = getGroupName(pos)
            return !TextUtils.equals(prevGroupId, groupId)
        }
    }
}