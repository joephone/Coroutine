package com.transcendence.coroutine.api

import com.transcendence.coroutine.log.LogUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author joephone
 * @date 2025/6/27 6:12
 * @description
 * @edition 1.0
 */


val userServiceApi : UserServiceApi by lazy {
    val loggingInterceptor = HttpLoggingInterceptor {
        LogUtils.d( "HttpLoggingInterceptor:$it" ) // 替换为你的日志工具
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY // 显示完整请求和响应内容
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain ->
            chain.proceed(chain.request()).apply {
                // 可添加自定义拦截逻辑
            }
        }
        .build()

    Retrofit.Builder()
        .client(client)
        .baseUrl(BaseUrl.WAN)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserServiceApi::class.java)
}
interface UserServiceApi {
//    @GET("user")
//    fun loadUser(@Query("name") name :String) : Call<User>

    @FormUrlEncoded
    @POST("user/login")
    fun login(
        @Field("username") username: String,
        @Field("password") pwd: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("user/login")
    suspend fun loginSuspend(
        @Field("username") username: String,
        @Field("password") pwd: String
    ): LoginResponse
}

class LoginResponse {
    val data: UserData? = null
    val errorCode: Int = 0
    val errorMsg: String? = null
}


class UserData {
    /**
     * admin : false
     * chapterTops : []
     * coinCount : 3645
     * collectIds : [17564,17629,3297,11077,21580]
     * email :
     * icon :
     * id : 60527
     * nickname : 15171484007
     * password :
     * publicName : 15171484007
     * token :
     * type : 0
     * username : 15171484007
     */
    var isAdmin = false
    var coinCount = 0
    var email: String? = null
    var icon: String? = null
    var id = 0
    var nickname: String? = null
    var password: String? = null
    var publicName: String? = null
    var token: String? = null
    var type = 0
    var username: String? = null
    var chapterTops: List<*>? = null
    var collectIds: List<Int>? = null
    //coinCount":3645,"level":37,"nickname":"","rank":"749","userId":60527
    /**********************************************我的积分 */
    var level = 0
    var rank: String? = null
}