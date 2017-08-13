package top.goluck.recyclerview_2017_8_13

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import top.goluck.recyclerview_2017_8_13.activity.Demo1Activity
import top.goluck.recyclerview_2017_8_13.activity.Demo2Activity
import top.goluck.recyclerview_2017_8_13.activity.Demo3Activity
import top.goluck.recyclerview_2017_8_13.activity.Demo4Activity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        demo1.setOnClickListener {
            MainActivity@ this.start(Demo1Activity::class.java)
        }
        demo2.setOnClickListener {
            MainActivity@ this.start(Demo2Activity::class.java)
        }
        demo3.setOnClickListener {
            MainActivity@ this.start(Demo3Activity::class.java)
        }
        demo4.setOnClickListener {
            MainActivity@ this.start(Demo4Activity::class.java)
        }

    }
}