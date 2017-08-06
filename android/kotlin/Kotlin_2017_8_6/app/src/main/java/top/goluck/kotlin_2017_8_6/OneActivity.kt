package top.goluck.kotlin_2017_8_6

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * Created by 员工 on 2017/8/6.
 */
class OneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setText()
        printArr()
        printBoolean()
        test()
    }

    //调用inline函数
    fun setText() {
        txt.text = toCompanion + "\n" + getClassName<OneActivity>(this)
        var a = "Hello ${txt.text}".toTop10String()
        txt.text = "$a\n" + toCompanion + "\n" + getClassName<OneActivity>(this)
    }


    //静态
    companion object {
        const val TIMER_DELAY: Long = 3000 // 定义long类型的全局静态变量
        @JvmField val mUser: User = User("小张", 25)  //定义any对象的全局静态变量
    }

    //延迟赋值
    val toCompanion: String by lazy {
        // 计算该字符串
        mUser.name + mUser.age + TIMER_DELAY
    }

    //数组操作
    fun printArr() {
        var arr = Array<String>(6, { "1232";"34";"45321";"4564";"1231" })
        arr.filter { it.contentEquals("1") }
                .forEach { println(it) }
    }


    //Boolen 可为null
    fun printBoolean() {
        var random = Random().nextInt()%100
        var type = {
            if(random==1 || random==3 || random==5){
                1
            }else if(random==2 || random==4 || random==7){
                2
            }else{
                3
            }
        }.invoke()
        val b: Boolean? = {
            if(type==1){
                null
            }else type==2
        }.invoke()
        if (b == true) {
            println(b)
        } else {
            println("b` 是 false 或者 null")
        }
    }

    //测试try catch
    fun test() {
        val result = try {
            "1231a".toInt()
        } catch (e: Exception) {
            23132
        }
        // 使用 result
        println("$result")
    }
}