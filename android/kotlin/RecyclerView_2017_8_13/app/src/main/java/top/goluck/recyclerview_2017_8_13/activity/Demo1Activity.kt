package top.goluck.recyclerview_2017_8_13.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_demo.*
import top.goluck.recyclerview_2017_8_13.decoration.PaddingDecoration
import top.goluck.recyclerview_2017_8_13.R
import top.goluck.recyclerview_2017_8_13.SimpleAdapter

class Demo1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        demo_rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        demo_rv.addItemDecoration(PaddingDecoration())
        demo_rv.adapter = SimpleAdapter(this, 2)
    }

}
