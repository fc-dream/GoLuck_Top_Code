package top.goluck.recyclerview_2017_9_17

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

/**
 * Created by luck on 2017/9/17.
 */

class MyRecyclerView(context: Context?, attrs: AttributeSet?) : RecyclerView(context,attrs) {

    constructor(context: Context?) : this(context,null) {
        RecyclerView(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : this(context,attrs) {
        RecyclerView(context, attrs, defStyle)
    }


    fun initAdapter(mAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        if (mAdapter == null) {
            return
        }
        var mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.orientation = OrientationHelper.VERTICAL
        layoutManager = mLayoutManager
        itemAnimator = DefaultItemAnimator()
        adapter = mAdapter
    }
}
