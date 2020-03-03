package com.csd.mvp.persenter

import android.app.Activity
import android.content.Context
import android.os.SystemClock
import com.csd.mvp.model.ResponseData
import com.csd.mvp.view.MainView
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.concurrent.thread


class MainPresenterImp constructor(context: Context) : MainPresenter {

    var mainView: MainView? = null
    var context: Context? = null

    init {
        this.context = context
    }

    override fun bindView(mainView: MainView) {
        this.mainView = mainView
    }

    override fun unBindView() {
        mainView = null
    }

    override fun doLogin(account: String, pwd: String) {
        if (account.isNotEmpty() && pwd.isNotEmpty()) {
            thread() {
                run {
                    (context as Activity).runOnUiThread { mainView!!.showProgress() }
                    SystemClock.sleep(3000)
                    (context as Activity).runOnUiThread { mainView!!.dissProgress() }

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
                            mainView!!.loginSuccess(responseData)
                        } else {
                            mainView!!.loginFailed(responseData.msg)

                        }
                    }
                }
            }
        } else {
            mainView!!.showToast("用户名和密码不能为空")
        }
    }
}