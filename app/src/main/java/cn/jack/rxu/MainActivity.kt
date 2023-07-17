package cn.jack.rxu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SingleU
            .just(111)
            .map(object : FunctionU<Int, String> {
                override fun apply(t: Int): String {
                    return "操作符$t"
                }
            })
            .subscribeOn()
            .observeOn()
            .subscribe(object : ObserverU<String> {
                override fun onSubscribe() {
                    println("触发时机 onSubscribe")
                }

                override fun onError() {
                    println("触发时机 onError")
                }

                override fun onSuccess(t: String) {
                    println("触发时机 onSuccess $t")
                }
            })
    }

}