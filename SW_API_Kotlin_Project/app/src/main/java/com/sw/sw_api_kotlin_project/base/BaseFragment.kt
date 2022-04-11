package com.sw.sw_api_kotlin_project.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment


open class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
    }

    open fun addObservers() {}
}