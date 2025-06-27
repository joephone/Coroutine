package com.transcendence.coroutine

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.transcendence.coroutine.api.userServiceApi
import com.transcendence.coroutine.log.LogUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author joephone
 * @date 2025/6/27 10:36
 * @description  MainScope  生命周期是process级别的，即使Activity或Fragment已经被销毁，协程仍然在执行。
 * @edition 1.0
 */
class MainActivity06 : AppCompatActivity(),CoroutineScope by MainScope() {

//    private val mainScope = MainScope()
    private var info :TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        info  = findViewById<TextView>(R.id.tv_info)
        info?.text ="joe"

        val submit  = findViewById<TextView>(R.id.tv_submit).also {
            it.setOnClickListener {
                launch {
                    val login = userServiceApi.loginSuspend("","123456")
                    info?.text =" name:${login.data?.username}"
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        mainScope.cancel()
        cancel()
    }
}