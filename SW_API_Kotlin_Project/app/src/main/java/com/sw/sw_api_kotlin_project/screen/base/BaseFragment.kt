package com.sw.sw_api_kotlin_project.screen.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sw.sw_api_kotlin_project.utils.Timber


open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.timberTrace(this::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.timberTrace(this::class.java)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addObservers()
    }

    override fun onStart() {
        super.onStart()
        Timber.timberTrace(this::class.java)
    }

    override fun onResume() {
        super.onResume()
        Timber.timberTrace(this::class.java)
    }

    override fun onStop() {
        super.onStop()
        Timber.timberTrace(this::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.timberTrace(this::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.timberTrace(this::class.java)
    }

    override fun onDetach() {
        super.onDetach()
        Timber.timberTrace(this::class.java)
    }

    open fun initView() {
        Timber.timberTrace(this::class.java)
    }

    open fun addObservers() {
        Timber.timberTrace(this::class.java)
    }
}