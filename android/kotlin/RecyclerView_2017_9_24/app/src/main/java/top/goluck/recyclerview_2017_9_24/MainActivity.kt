package top.goluck.recyclerview_2017_9_24

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import top.goluck.recyclerview_2017_9_17.Data
import top.goluck.recyclerview_2017_9_17.MainAdpater
import top.goluck.recyclerview_2017_9_17.MyRecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var mMainAdpater: MainAdpater
    lateinit var main_reyclerview: MyRecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_reyclerview = findViewById(R.id.main_reyclerview)
        mMainAdpater = MainAdpater(this)
        var data = getData()

        var itemdata = Data()
        itemdata.type = 2
        data.add(15,itemdata)//添加自定义位置的不复用View

        mMainAdpater.setData(data)

        var txt1 = TextView(this)
        txt1.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,400)
        txt1.text = "我添加的HeadView"
        txt1.gravity = Gravity.CENTER
        txt1.setTextColor(Color.GREEN)
        txt1.setBackgroundColor(Color.YELLOW)
        mMainAdpater.addHeaderView(txt1)//添加一个HeaderView


        var txt2 = TextView(this)
        txt2.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200)
        txt2.text = "我添加的FootView"
        txt2.setTextColor(Color.RED)
        txt2.gravity = Gravity.CENTER
        txt2.setBackgroundColor(Color.BLUE)
        mMainAdpater.addFooterView(txt2)//添加一个FootView

        main_reyclerview.initAdapter(mMainAdpater)

    }

    fun getData(): ArrayList<Data> {
        var datas = ArrayList<Data>()
        var position = 0
        for (i in 0..3) {
            for (j in 0..9) {
                var data = Data()
                data.id = i * 9 + j
                if (j == 0) {
                    data.type = 0
                    data.position = position
                    data.context = "我是type=0数据，我是标题：" + (i + 1)
                } else {
                    data.type = 1
                    data.position = position
                    data.context = "我是type=1数据，我是第" + (i + 1) + "组的数据，内容数据：" + (j + 1)
                }
                datas.add(data)
            }
            position = (i + 1) * 10
        }
        return datas
    }
}
