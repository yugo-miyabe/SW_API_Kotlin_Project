package com.sw.sw_api_kotlin_project.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        initData()
    }

    abstract fun initViews()


    abstract fun initData()


}