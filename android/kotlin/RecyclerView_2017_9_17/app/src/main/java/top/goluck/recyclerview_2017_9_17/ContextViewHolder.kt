package top.goluck.recyclerview_2017_9_17

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by luck on 2017/9/17.
 */
class ContextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var context_txt: TextView

    init {
        context_txt = itemView.findViewById(R.id.context_txt)
    }

    fun bindDataandListener(position: Int, data: Data) {
        context_txt.text = ""+position+data.context
    }

}