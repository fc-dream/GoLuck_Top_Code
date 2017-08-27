package top.goluck.aidl_2017_8_27.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import top.goluck.aidl_2017_8_27.aidl.Book
import top.goluck.aidl_2017_8_27.aidl.IBookManager

/**
 * Created by luck on 2017/8/27.
 */
class AidlService : Service() {
    override fun onBind(p0: Intent?): IBinder {
        return mIBinder
    }

    val mIBinder: IBinder = object : IBookManager.Stub() {
        override fun sendBook(book: Book?) {
            var mBook = Book("Android开发的艺术", 66.6f)
            book?.name = mBook.name
            book?.price = mBook.price
            Log.i("service:", "-------------sendBook:" + book.toString())
        }

        override fun udpateBook(book: Book?) {
            var mBook = Book("Android开发的艺术", 45.9f)
            book?.name = mBook.name
            book?.price = mBook.price
            Log.i("service:", "-------------udpateBook:" + book.toString())
        }

        override fun basicTypes(aInt: Int, aLong: Long, aBoolean: Boolean, aFloat: Float,
                                aDouble: Double, aString: String): String? {
            var result: String? = "服务端收到，这是给你的回复 aInt=$aInt \naLong = $aLong aBoolean = $aBoolean \n aFloat = $aFloat \n aDouble = $aDouble \n aString= $aString \n aLong = $aLong  \n aInt = $aInt"
            Log.i("service:", "-------------basicTypes:" + result)
            return result
        }
    }

}