package top.goluck.recyclerview_2017_9_17

import android.graphics.Color
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import top.goluck.recyclerview_2017_9_24.HeaderAndFooterAdapter
import top.goluck.recyclerview_2017_9_24.R

/**
 * Created by luck on 2017/9/17.
 */

class MainAdpater : HeaderAndFooterAdapter<Data>() {

    override fun onNewBindViewHolder(holder: ViewHolder, position: Int, payloads: List<*>?) {
        if (holder is Title1ViewHolder) {
            holder.bindDataandListener(position, data[position])
        } else if (holder is ContextViewHolder) {
            holder.bindDataandListener(position, data[position])
        }
    }

    override val newItemCount: Int
        get() = data.size

    override fun onNewCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == 0) {
            return Title1ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.title_type1, parent, false))
        } else if (viewType == 1) {
            return ContextViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.context, parent, false))
        } else {
            var txt = TextView(parent.context)
            txt.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,300)
            txt.text = "我是自定义的不复用地View"
            txt.setTextColor(Color.WHITE)
            txt.gravity = Gravity.CENTER
            txt.setBackgroundColor(Color.GREEN)
            var vh = object : ViewHolder(txt) {}
            vh.setIsRecyclable(false)
            return vh
        }
    }

    override fun getNewItemViewType(position: Int): Int {
        return data[position].type
    }
}
