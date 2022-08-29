package com.sw.sw_api_kotlin_project.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sw.sw_api_kotlin_project.utils.Utils


open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.timberTrace(this::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Utils.timberTrace(this::class.java)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun onDestroyView() {
        super.onDestroyView()
        Utils.timberTrace(this::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        Utils.timberTrace(this::class.java)
    }

    override fun onDetach() {
        super.onDetach()
        Utils.timberTrace(this::class.java)
    }

    open fun initView(){
        Utils.timberTrace(this::class.java)
    }


    open fun addObservers() {
        Utils.timberTrace(this::class.java)
    }
}