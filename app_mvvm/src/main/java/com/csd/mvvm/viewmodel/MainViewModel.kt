package com.csd.mvvm.viewmodel

import android.app.Activity
import android.content.Context
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import com.csd.mvvm.model.ResponseData
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.concurrent.thread


class MainViewModel constructor(context: Context) {

    var context: Context? = null

    init {
        this.context = context
    }

    public var account: ObservableField<String> = ObservableField("")
    public var pwd: ObservableField<String> = ObservableField("")
    public var showPro: ObservableInt = ObservableInt(View.GONE)

    fun doLogin(view: View) {
        if (account.get()!!.isNotEmpty() && pwd.get()!!.isNotEmpty()) {
            thread() {
                run {
                    (context as Activity).runOnUiThread { showPro.set(View.VISIBLE) }
                    SystemClock.sleep(3000)
                    (context as Activity).runOnUiThread { showPro.set(View.GONE) }

                    val jsonObject = JSONObject()
                    val random = Random()
                    if (random.nextBoolean()) {
                        try {
                            jsonObject.put("ret", "1")
                            jsonObject.put("msg", "登录成功")
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }

                    } else {
                        try {
                            jsonObject.put("ret", "-1")
                            jsonObject.put("msg", "登录失败")
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }

                    }

                    (context as Activity).runOnUiThread {
                        val responseData = ResponseData.paseFromJson(jsonObject)
                        if ("1" == responseData.ret) {
                            Toast.makeText(context, responseData.msg, Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, responseData.msg, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        } else {
            Toast.makeText(context, "用户名和密码不能为空", Toast.LENGTH_LONG).show()

        }
    }
}