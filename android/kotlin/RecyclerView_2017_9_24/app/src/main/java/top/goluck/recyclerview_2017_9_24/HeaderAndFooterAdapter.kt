package top.goluck.recyclerview_2017_9_24

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by luck on 2017/9/24.
 */
abstract class HeaderAndFooterAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    protected var data=ArrayList<T>()
    private val mHeaderViews = SparseArrayCompat<View>()
    private val mFootViews = SparseArrayCompat<View>()

    /**
     * 获取指定position对于的T

     * @param p
     * *
     * @return
     */
    fun getDataT(p: Int): T? {
        if (p > data.size || p < 0) {
            return null
        }
        return data[p]
    }

    /**
     * 设置指定positioin对应的T

     * @param p
     * *
     * @param t
     */
    fun setDataT(p: Int, t: T) {
        data[p] = t
        notifyItemChanged(p)
    }

    /**
     * 清空数据但不通知刷新界面
     */
    fun clearNotNotify() {
        if (data != null) {
            data.clear()
        }
    }

    private var off = false

    /**
     * 设置添加数据是否清空之前的数据

     * @param off
     */
    fun clearNotNotify(off: Boolean) {
        this.off = off
    }

    /**
     * 清空数据并刷新界面
     */
    fun clear() {
        if (data != null) {
            data.clear()
            notifyDataSetChanged()
        }
    }

    /**
     * 添加集合数据并通知刷新界面 (注： 如果off设置为true，会先清空之前的数据)

     * @param datas
     */
    fun addData(datas: List<T>?) {
        if (datas != null) {
            if (off) {
                data.clear()
                off = false
            }
            data.addAll(datas)
            notifyDataSetChanged()
        }
    }

    /**
     * 添加单个数据但不通知刷新界面

     * @param mData
     */

    fun addData(mData: T) {
        if (data != null) {
            data.add(mData)
        }
    }

    /**
     * 添加集合数据但不通知刷新界面

     * @param datas
     */
    fun addDataNotNotify(datas: List<T>?) {
        if (datas != null) {
            data.addAll(datas)
        }
    }

    /**
     * 重置数据并刷新通知

     * @param data
     */
    fun setData(data: List<T>) {
        if (data != null) {
            this.data = data as ArrayList<T>
        } else {
            this.data.clear()
        }
        if (off) {
            off = false
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (mHeaderViews.get(viewType) != null) {
            val holder = object : RecyclerView.ViewHolder(mHeaderViews.get(viewType)) {

            }
            holder.setIsRecyclable(false)
            return holder
        } else if (mFootViews.get(viewType) != null) {
            val holder = object : RecyclerView.ViewHolder(mFootViews.get(viewType)) {

            }
            holder.setIsRecyclable(false)
            return holder
        }
        return onNewCreateViewHolder(parent, viewType)
    }

    private fun isHeaderViewPos(position: Int): Boolean {
        return position < headersCount
    }

    private fun isFooterViewPos(position: Int): Boolean {
        return position >= headersCount + newItemCount
    }

    fun addHeaderView(view: View) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view)
    }

    fun addFooterView(view: View) {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view)
    }

    val headersCount: Int
    get() = mHeaderViews.size()

    val footersCount: Int
    get() = mFootViews.size()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<*>?) {
        if (isHeaderViewPos(position)) {
            return
        }
        if (isFooterViewPos(position)) {
            return
        }

        onNewBindViewHolder(holder, position - headersCount, payloads)
    }

    override fun getItemCount(): Int {
        return headersCount + footersCount + newItemCount
    }

    override fun getItemViewType(position: Int): Int {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position)
        } else if (isFooterViewPos(position)) {
            return mFootViews.keyAt(position - headersCount - newItemCount)
        }
        return getNewItemViewType(position - headersCount)
    }

    /**
     * 要显示的item

     * @param parent
     * *
     * @param viewType 对应的viewType
     * *
     * @return
     */
    abstract fun onNewCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    /**
     * 绑定item

     * @param holder   对应viewType的holder
     * *
     * @param position list的下标
     */
    abstract fun onNewBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<*>?)

    /**
     * 要展示的item个数

     * @return
     */
    abstract val newItemCount: Int

    /**
     * 设置要展示的ItemView对应的type

     * @return
     */
    abstract fun getNewItemViewType(position: Int): Int

    companion object {
        const private val BASE_ITEM_TYPE_HEADER = 1000000
        const private val BASE_ITEM_TYPE_FOOTER = 2000000
    }
}