package com.csd.mvp.persenter

import com.csd.mvp.view.MainView

interface MainPresenter : BasePresenter<MainView> {

    fun doLogin(account: String, pwd: String)

}