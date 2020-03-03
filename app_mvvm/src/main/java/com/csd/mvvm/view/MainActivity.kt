package com.csd.mvvm.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import com.csd.mvvm.databinding.ActivityMainBinding
import com.csd.mvvm.R
import com.csd.mvvm.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private var activityMainBinding: ActivityMainBinding? = null
    private var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        mainViewModel = MainViewModel(this)
        activityMainBinding!!.mainViewModel = mainViewModel
        activityMainBinding!!.etAccount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mainViewModel!!.account!!.set(s.toString())
            }

        })
        activityMainBinding!!.etPwd.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mainViewModel!!.pwd!!.set(s.toString())
            }

        })
    }

}
