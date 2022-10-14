package com.sw.sw_api_kotlin_project.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sw.sw_api_kotlin_project.utils.Utils

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        addObservers()
    }

    override fun onStart() {
        super.onStart()
        Utils.timberTrace(this::class.java)
    }

    override fun onResume() {
        super.onResume()
        Utils.timberTrace(this::class.java)
    }

    override fun onStop() {
        super.onStop()
        Utils.timberTrace(this::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        Utils.timberTrace(this::class.java)
    }

    open fun initView() {
        Utils.timberTrace(this::class.java)
    }

    open fun addObservers() {
        Utils.timberTrace(this::class.java)
    }
}