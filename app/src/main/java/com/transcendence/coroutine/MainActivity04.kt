package com.transcendence.coroutine

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.transcendence.coroutine.log.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author joephone
 * @date 2025/6/27 9:14
 * @description  挂起与阻塞
 * @edition 1.0
 */
class MainActivity04 : AppCompatActivity() {

    var info :TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        info  = findViewById<TextView>(R.id.tv_info)

        val submit  = findViewById<TextView>(R.id.tv_submit).also {
            it.setOnClickListener {
                // GlobalScope（全局作用域） 生命周期与 整个应用程序进程 绑定（协程会一直运行，直到进程结束或手动取消）
                // launch是一个 协程启动函数，用于创建并启动一个新的协程
                GlobalScope.launch(Dispatchers.Main) {  //父协程
                    //挂起
                    delay(10000)
                    LogUtils.d("${Thread.currentThread().name}  delay")
                }
                // 阻塞
                Thread.sleep(10000)
                LogUtils.d("${Thread.currentThread().name}  after sleep")
            }
        }
    }
}