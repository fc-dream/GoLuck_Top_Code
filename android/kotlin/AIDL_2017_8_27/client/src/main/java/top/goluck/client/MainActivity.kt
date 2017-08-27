package top.goluck.client

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.ComponentName
import android.os.IBinder
import android.content.ServiceConnection
import android.os.RemoteException
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import top.goluck.aidl_2017_8_27.aidl.Book
import top.goluck.aidl_2017_8_27.aidl.IBookManager


class MainActivity : AppCompatActivity() {

    lateinit var mBookManager: IBookManager
    var mBound: Boolean = false
    var mBook1: Book = Book()
    var mBook2: Book = Book("Android第一行代码", 49.9f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent()
        intent.action = "top.goluck.aidl_2017_8_27.service"
        intent.`package` = "top.goluck.aidl_2017_8_27"
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
        Log.i("client", "-------------bindService")
    }

    override fun onStop() {
        super.onStop()
        if (mBound) {
            unbindService(mServiceConnection)
            mBound = false
        }
    }

    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Log.i("client", "-------------service connected")
            mBookManager = IBookManager.Stub.asInterface(service)
            mBound = true

            if (mBookManager != null) {
                try {
                    mBookManager.udpateBook(mBook1)
                    mBookManager.sendBook(mBook2)

                    var result = mBook1.toString() +"\n" + mBook2.toString() +"\n"  + mBookManager.basicTypes(1,1231,true,34.5f, 123123.0,"我是可是客户端传入的数据")
                    txt.text = result
                    Log.i("-------------client", result)
                } catch (e: RemoteException) {
                    e.printStackTrace()

                    Log.i("-------------client", "错误")
                    txt.text = "没有获取到相应进程的通信数据"
                }

            }
        }

        override fun onServiceDisconnected(name: ComponentName) {
            Log.i("client", "-------------service disconnected")
            mBound = false
        }
    }
}
