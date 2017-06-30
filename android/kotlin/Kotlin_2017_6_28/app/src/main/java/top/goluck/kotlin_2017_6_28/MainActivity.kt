package top.goluck.kotlin_2017_6_28

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import top.goluck.kotlin_2017_6_28.util.*

class MainActivity : AppCompatActivity() {

    val tag = "Main"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var user1 = Util()
        var a = user1.sum(1, 2)
        Log.i(tag, "user1.sum(1, 2)=" + a)

        var user2 = Util(1)
        Log.i(tag, "user2.sum(1, 2, 3)=" + user2.sum(1, 2, 3))
        user2.density
        Log.i(tag, "user2.density=" + user2.density)

        var util3 = Util3(1)
        Log.i(tag, "util3.toString1()=" + util3.toString1())

        var ah = util3.toInt(12313)
        Log.i(tag, "ah=" + ah)

        var user = User.instance
        user?.age = 18.toString()
        user!!.name = "张三"
        Log.i(tag, "" + user.age + "" + user.name)

        //also 应用 !! ?
        Log.i(tag, "" + User.instance.also { Log.i(tag, User.instance!!.age + "" + User.instance?.name) }!!.age)
        //apply 应用
        text.apply {
            var a = "1 + 2 + 3 ="
            var b = user2.sum(1, 2, 3)
            var sum = "$a$b"
            text = sum
            setTextColor(Color.BLUE)
            var two_sum = "$sum\n$sum"
            setText(two_sum)//属性覆盖测试
            var three_sum = "$two_sum\n$two_sum"
            text = three_sum//属性覆盖测试
            setBackgroundColor(Color.YELLOW)
        }
        // for
        var arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        for (i in arr) {
            print(i)
        }
        //if else when
        var iew = 9
        when (iew) {
            1 -> Log.i(tag, "---------1")
            in listOf(1, 2, 9) -> Log.i(tag, "---------listOf")
            if (iew == 9) 1 else -1 -> Log.i(tag, "---------==9")
            is Int  -> Log.i(tag, "---------is Int")
            else -> Log.i(tag, "---------else")
        }

        //集合
        var list = listOf("111", "22423", "333", "124323433")
        list.filter { it.endsWith("3") }
//                .forEach(fun (it:String){//方法1
//                    Log.i(tag, "$it")
//                })
                .forEach { Log.i(tag, "$it") }//方法2

        //集合嵌套
        var lists = listOf(list, list)
        lists.forEach(fun(its: List<String>) {
            its.forEach(action = fun(it: String) {
                Log.i(tag, "$its $it")
            })
        })

        //x@ 嵌套 加 函数
        val end = end@ {
            x1: Int ->
            return@end {
                x2: Int ->
                beg@ {
                    return@beg x1 + x2
                }
            }
        }
        val aa = end.invoke(1).invoke(2).invoke()
        Log.i(tag, "end=$aa")

        //x@标签 加 函数
        val fool = endandfun@ {
            x: Int, y: Int ->
            return@endandfun x + y
        }
//        val bb = fool.invoke(1,2)
        val bb = fool(1, 2)
        Log.i(tag, "fool=$bb")

        //内部函数赋值
        fun add(x: Int, y: Int): Int {
            val a = { z1: Int, z2: Int ->{ d1: Int, d2: Int ->//lambda形式
                    z1 + z2 + d1 * d1 + d2 * d2 //(1 + 2 +1* 1 + 1 *1)
                }.invoke(x, z1)
            }
            var z = a.invoke(1, 2)

            fun add(x:Int,y:Int,z:Int):Int{
                return x + y + z//1 + 2 + 3
            }
            val b : (Int,Int,Int) -> Int = ::add
            var w = b(1,2,3)
            return x + y + z + w//1 + 2 + 5 + 6
        }
        var cc = add(1, 2)
        Log.i(tag, "cc=$cc")

        //run可以带返回参数，该方法自动生成标签
        var dd:Int = run({
            return@run 1
        })
        Log.i(tag, "dd=$dd")



    }
}
