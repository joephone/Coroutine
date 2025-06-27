package com.transcendence.coroutine

import android.os.AsyncTask
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.transcendence.coroutine.api.LoginResponse
import com.transcendence.coroutine.api.userServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author joephone
 * @date 2025/6/27 8:33
 * @description  全局作用域协程写法
 * @edition 1.0
 */
class MainActivity02 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val info  = findViewById<TextView>(R.id.tv_info)

        val submit  = findViewById<TextView>(R.id.tv_submit).also {
            it.setOnClickListener {
                // GlobalScope（全局作用域） 生命周期与 整个应用程序进程 绑定（协程会一直运行，直到进程结束或手动取消）
                // launch是一个 协程启动函数，用于创建并启动一个新的协程
                GlobalScope.launch(Dispatchers.Main) {  //父协程
                    // Dispatchers 任务调度器
                    val login = withContext(Dispatchers.IO) {   //子协程
                        //填自己的用户名与密码
                        userServiceApi.loginSuspend("","")
                    }
                    info.text =" name:${login.data?.username}"
                }
            }
        }
    }
}