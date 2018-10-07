package top.goluck.applinks_2018_10_7

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webview.loadUrl("file:///android_asset/index.html")
        getTopIntent()
    }

    fun getTopIntent(){
        if (null != intent && null != intent.data) {
            // uri 就相当于 web 页面中的链接
            var uri = intent.data
            text.text = "uri = " +uri
            text.text = text.text.toString() + "\n scheme = " + uri.scheme
            text.text = text.text.toString() + "\n host = " + uri.host
            text.text = text.text.toString() + "\n key1 = " + uri.getQueryParameter("key1")
            text.text = text.text.toString() + "\n key2 = " + uri.getQueryParameter("key2")
        }
    }

}