package top.goluck.kotlin_2017_8_20

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //数字
        var dou: Double = 1231.0
        var flo: Float = 123F
        var lon: Long = 1_000_000
        var int: Int = 123
        var sho: Short = 123
        var byt: Byte = 0b00001011

        //类型转换
        var toInt: Int = flo.toInt()

        //字符
        var charToInt: Int = decimalDigitValue('1')

        //布尔
        var boo : Boolean = (int%1 == 0 && sho%3 == 1)

        //数组
        var arr1: Array<Int?> = arrayOfNulls(3)
        var arr2 = arrayOf(1, 2, 3)


        //label@ 使用
        print(getValues(10,11))
        print(getValues(10,9))
        print(getValues(10,10))
    }

    fun decimalDigitValue(c: Char): Int {
        if (c !in '0'..'9')
            throw IllegalArgumentException("Out of range")
        return c.toInt() - '0'.toInt() // 显式转换为数字
    }

    fun getValues(imax:Int,jmax:Int):Int{
        loop@ for (i in 1..100) {
            for (j in 1..100) {
                if (j==jmax) break@loop //该break执行将会除非第一个for停止
                if(i==jmax) {
                    return getValues@ i * j
                }
            }
            if(i==imax){
                break@loop //该break执行将会除非第一个for停止
            }
        }
        return 0
    }

}
