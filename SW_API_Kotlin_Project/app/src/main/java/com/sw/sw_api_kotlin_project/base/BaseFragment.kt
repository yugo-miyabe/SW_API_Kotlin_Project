package com.sw.sw_api_kotlin_project.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(this::class.java.simpleName, Thread.currentThread().stackTrace[2].methodName)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(this::class.java.simpleName, Thread.currentThread().stackTrace[2].methodName)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
    }

    override fun onStart() {
        super.onStart()
        Log.d(this::class.java.simpleName, Thread.currentThread().stackTrace[2].methodName)
    }

    override fun onResume() {
        super.onResume()
        Log.d(this::class.java.simpleName, Thread.currentThread().stackTrace[2].methodName)
    }

    override fun onStop() {
        super.onStop()
        Log.d(this::class.java.simpleName, Thread.currentThread().stackTrace[2].methodName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(this::class.java.simpleName, Thread.currentThread().stackTrace[2].methodName)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(this::class.java.simpleName, Thread.currentThread().stackTrace[2].methodName)
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(this::class.java.simpleName, Thread.currentThread().stackTrace[2].methodName)
    }

    open fun addObservers() {
        Log.d(this::class.java.simpleName, Thread.currentThread().stackTrace[2].methodName)
    }

}