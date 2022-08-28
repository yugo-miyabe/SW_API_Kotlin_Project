package com.sw.sw_api_kotlin_project.base

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sw.sw_api_kotlin_project.R

abstract class BaseActivity : AppCompatActivity() {

    private var mProgressDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        initData()
    }

    abstract fun initViews()


    abstract fun initData()


    fun showBlockingProgress() {
        if (this.isFinishing) {
            return
        }
        if (mProgressDialog == null) {
            mProgressDialog = Dialog(this, R.style.Theme_SW_API_Kotlin_Project)
            mProgressDialog!!.setContentView(R.layout.view_progress)
            mProgressDialog!!.setCancelable(false)
            mProgressDialog!!.show()
        }
    }

    fun hideBlockingProgress() {
        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
            mProgressDialog = null
        }
    }

}