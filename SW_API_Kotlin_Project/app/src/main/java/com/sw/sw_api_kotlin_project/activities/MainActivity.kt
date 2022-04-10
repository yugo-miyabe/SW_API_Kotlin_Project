package com.sw.sw_api_kotlin_project.activities

import android.os.Bundle
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.base.BaseActivity
import com.sw.sw_api_kotlin_project.fragments.HomeFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initViews() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, HomeFragment())
            .commit()
    }


    override fun initData() {
        // 何もしない
    }

}