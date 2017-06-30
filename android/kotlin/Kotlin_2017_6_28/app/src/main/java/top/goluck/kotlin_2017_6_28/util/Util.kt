package top.goluck.kotlin_2017_6_28.util

import android.view.ViewGroup

/**
 * Created by 员工 on 2017/6/28.
 */
open class Util {//多二级构造 内部扩展Int 简单函数的调用返回
    private var num:Int = 0
    constructor()
    constructor(a:Int){
        num = a
    }
    var density: Int = 0
    fun Int.dpToPx(): Int {
        if (toInt() in intArrayOf(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)) {
            return this
        }
        return (this * density).toInt() //这里的Gloabl.density是在应用启动时获取的
    }
    fun sum(a:Int,b:Int)={
        a + b.dpToPx()
    }.invoke()

    fun sum(c:Int,d:Int,e:Int):Int{
        return c + d + e
    }
}

class Util3(a: Int) : Util(a) {//继承

//    constructor():super()

//    constructor(a:Int):super(a)

    fun toString1(): String {
        return "aaa"
    }
}

fun Any?.toInt(a:Any?): Int {//给所有kt类扩展方法
    if (this==null || a == null) {
        return 0
    }
    var b = this.toString()
    return b.length + a.let { it -> it.toString().length }
}

class User {//单例
    companion object {
        @Volatile var instance: User? = null
            get() {
                if (field == null) {
                    synchronized(User::class.java) {
                        if (field == null)
                            field = User()
                    }
                }
                return field
            }
    }

    var name: String? = null
    var age: String? = null
}