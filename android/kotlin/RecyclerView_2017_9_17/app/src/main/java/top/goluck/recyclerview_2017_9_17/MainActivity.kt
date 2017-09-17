package top.goluck.recyclerview_2017_9_17

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


/**
 * Created by luck on 2017/9/17.
 */

class MainActivity : AppCompatActivity() {

    lateinit var mMainAdpater: MainAdpater
    lateinit var main_reyclerview: MyRecyclerView
    lateinit var title1: ConstraintLayout
    lateinit var view1: Title1ViewHolder
    lateinit var mLinearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_reyclerview = findViewById(R.id.main_reyclerview)
        title1 = findViewById(R.id.title1)
        view1 = Title1ViewHolder(title1)
        mMainAdpater = MainAdpater()
        mMainAdpater.setData(getData())
        view1.bindDataandListener(0, if (mMainAdpater.getItemViewType(0) == 0) getData().get(0) else null)
        main_reyclerview.initAdapter(mMainAdpater)
        mLinearLayoutManager = main_reyclerview.layoutManager as LinearLayoutManager
        initHead()
    }

    fun getData(): ArrayList<Data> {
        var datas = ArrayList<Data>()
        for (i in 0..3) {
            for (j in 0..9) {
                var data = Data()
                data.id = i * 9 + j
                if (j == 0) {
                    data.type = 0
                    data.context = "我是type=0数据，我是标题：" + (i + 1)
                } else {
                    data.type = 1
                    data.context = "我是type=1数据，我是第" + (i + 1) + "组的数据，内容数据：" + (j + 1)
                }
                datas.add(data)
            }
        }
        return datas
    }

    fun initHead() {
        var h1: Float = 0f
        var h2: Float = 0f
        var mCurrentPosition: Int = 0
        main_reyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                h1 = view1.itemView.height.toFloat()
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (mMainAdpater.getItemViewType(mCurrentPosition + 1) == 0) {
                    val view = mLinearLayoutManager.findViewByPosition(mCurrentPosition + 1)
                    if (view != null) {
                        if (view!!.top <= h1) {
                            view1.itemView.y = -(h1 - view.top)
                        } else {
                            if (view1.itemView.y != 0f) {
                                view1.itemView.y = 0f
                            }
                        }
                    }
                }
                if (mCurrentPosition != mLinearLayoutManager.findFirstVisibleItemPosition()) {
                    mCurrentPosition = mLinearLayoutManager.findFirstVisibleItemPosition()
                    if (view1.itemView.y != 0f) {
                        view1.itemView.y = 0f
                    }
                    if (mCurrentPosition < getData().size && getData().get(mCurrentPosition).type == 0) {
                        view1.bindDataandListener(mCurrentPosition, getData().get(mCurrentPosition))
                    }
                }
            }
        })
    }
}
