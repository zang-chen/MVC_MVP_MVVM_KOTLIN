package com.csd.mvp.persenter

interface BasePresenter<T> {

    fun bindView(v: T)
    fun unBindView()

}