package top.goluck.recyclerview_2017_8_13

import android.app.Activity
import android.content.Intent
/**
 * Created by luck on 2017/8/13.
 */
inline fun Activity.start(java: Class<out Activity>) {
    var mInt = Intent(this, java)
    startActivity(mInt)
}

var titles: Array<String> = Array(10, { i -> "我是第 ${i+1} 个标题" })
var contexts: Array<String> = Array(100, { i -> "我是总第${i+1} 个内容\n我是第 ${(i+1) / 10} 个标题的第  ${(i+1) / 10}  个内容" })

