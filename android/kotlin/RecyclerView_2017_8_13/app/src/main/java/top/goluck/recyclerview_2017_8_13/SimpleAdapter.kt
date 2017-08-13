package top.goluck.recyclerview_2017_8_13

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by luck on 2017/8/13.
 */
class SimpleAdapter() : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {

    private lateinit var mContext: Context
    companion object {
        var mType:Int = 0
    }

    constructor(context: Context,type:Int) : this() {
        this.mContext = context
        mType = type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_context, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contexts.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder is ViewHolder) {
            holder.bindData(position)
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tv_context: TextView = view.findViewById(R.id.tv_context)
        var context: LinearLayout = view.findViewById(R.id.context)

        fun bindData(position: Int) {
            tv_context.text = contexts.get(position)
            itemView.tag = position
            if (mType == 1)
            context.setBackgroundColor(context.resources.getColor(android.R.color.transparent))
            else
            context.setBackgroundColor(context.resources.getColor(R.color.colorPrimary))
        }
    }
}