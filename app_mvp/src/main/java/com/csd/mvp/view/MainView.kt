package com.csd.mvp.view

import com.csd.mvp.model.ResponseData

interface MainView : BaseView {

    fun loginSuccess(responseData: ResponseData)
    fun loginFailed(error: String?)

}