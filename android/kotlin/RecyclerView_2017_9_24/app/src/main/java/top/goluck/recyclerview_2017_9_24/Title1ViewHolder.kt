package top.goluck.recyclerview_2017_9_17

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import top.goluck.recyclerview_2017_9_24.R

/**
 * Created by luck on 2017/9/17.
 */
class Title1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var title_type1_txt: TextView = itemView.findViewById(R.id.title_type1_txt)

    fun bindDataandListener(position: Int, data: Data?) {
        if (data == null) {
            itemView.visibility = View.GONE
        } else {
            itemView.visibility = View.VISIBLE
            title_type1_txt.text = "" + data.context + "\n编号：" + position
        }
    }

}