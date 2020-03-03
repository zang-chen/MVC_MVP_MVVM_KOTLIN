package com.csd.mvp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.csd.mvp.R
import com.csd.mvp.model.ResponseData
import com.csd.mvp.persenter.MainPresenterImp
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView {

    private var mainPresenterImp: MainPresenterImp? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenterImp = MainPresenterImp(this@MainActivity)
        mainPresenterImp!!.bindView(this)
        bu_login!!.setOnClickListener {
            var account: String = et_account.text.toString()
            var pwd: String = et_pwd.text.toString()
            mainPresenterImp!!.doLogin(account, pwd)
        }
    }


    override fun loginSuccess(responseData: ResponseData) {
        Toast.makeText(this@MainActivity, responseData.msg, Toast.LENGTH_LONG).show()
    }


    override fun loginFailed(error: String?) {
        Toast.makeText(this@MainActivity, error, Toast.LENGTH_LONG).show()
    }

    override fun showToast(msg: String) {
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE

    }

    override fun dissProgress() {
        progressBar.visibility = View.GONE
    }
}
