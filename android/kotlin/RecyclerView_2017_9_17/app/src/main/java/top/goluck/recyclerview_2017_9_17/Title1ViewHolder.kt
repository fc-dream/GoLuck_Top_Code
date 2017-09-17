package top.goluck.recyclerview_2017_9_17

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by luck on 2017/9/17.
 */
class Title1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var title_type1_img: ImageView
    var title_type1_txt: TextView

    init {
        title_type1_txt = itemView.findViewById(R.id.title_type1_txt)
        title_type1_img = itemView.findViewById(R.id.title_type1_img)
    }

    fun bindDataandListener(position:Int,data:Data){
        title_type1_txt.text = ""+data.context+position
    }

}