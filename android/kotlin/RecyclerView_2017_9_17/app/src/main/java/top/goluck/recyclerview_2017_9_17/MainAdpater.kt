package top.goluck.recyclerview_2017_9_17

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by luck on 2017/9/17.
 */

class MainAdpater : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data=ArrayList<Data>()

    fun setData(itemdata:ArrayList<Data>){
        if (itemdata != null) {
            data = itemdata
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        if(viewType==0){
            return Title1ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.title_type1, parent, false))
        }else  if(viewType==1){
            return ContextViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.context, parent, false))
        }
        return null
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is Title1ViewHolder){
            holder.bindDataandListener(position,data.get(position))
        }else if(holder is ContextViewHolder){
            holder.bindDataandListener(position,data.get(position))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }
}
