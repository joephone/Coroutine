package com.transcendence.coroutine

import android.os.AsyncTask
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * @author joephone
 * @date 2025/6/27 5:56
 * @description
 * @edition 1.0
 */
class MainActivity01 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val info  = findViewById<TextView>(R.id.tv_info)

        val submit  = findViewById<TextView>(R.id.tv_submit).also {
            it.setOnClickListener {
                object : AsyncTask<Void,Void,LoginResponse>(){
                    override fun doInBackground(vararg params: Void?): LoginResponse? {
                        //填自己的用户名与密码
                        return userServiceApi.login("","").execute().body()
                    }

                    override fun onPostExecute(result: LoginResponse?) {
//                        super.onPostExecute(result)
                        info.text =" name:${result?.data?.username}"
                    }
                }.execute()
            }
        }
    }
}