package com.csd.mvc

import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bu_login!!.setOnClickListener {
            var account: String = et_account!!.text.toString()
            var pwd: String = et_pwd!!.text.toString()
            doLogin(account, pwd)
        }
    }

    fun doLogin(account: String, pwd: String) {
        if (account.isNotEmpty() && pwd.isNotEmpty()) {
            thread() {
                run {
                    runOnUiThread { progressBar!!.visibility = View.VISIBLE }
                    SystemClock.sleep(3000)
                    runOnUiThread { progressBar!!.visibility = View.GONE }

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

                    runOnUiThread {
                        val responseData = ResponseData.paseFromJson(jsonObject)
                        if ("1" == responseData.ret) {
                            Toast.makeText(this@MainActivity, responseData.msg, Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this@MainActivity, responseData.msg, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        } else {
            Toast.makeText(this@MainActivity, "用户名和密码不能为空", Toast.LENGTH_LONG).show()

        }
    }
}
