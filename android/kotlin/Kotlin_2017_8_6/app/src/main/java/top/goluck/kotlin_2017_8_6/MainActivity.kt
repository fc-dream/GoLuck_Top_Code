package top.goluck.kotlin_2017_8_6

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setText()
        startActivity()
        txt.setOnClickListener { startActivity() }
    }

    fun setText() {
        txt.text = "全局静态Long类型变量："+OneActivity.TIMER_DELAY + "\n" + getClassName<MainActivity>(this) +"\n"+ OneActivity.mUser.name
    }

    fun startActivity(){
        var mInt = Intent(this, OneActivity::class.java)
        startActivity(mInt)
    }
}