package com.sw.sw_api_kotlin_project.data.liveData

import androidx.lifecycle.Observer
import com.sw.sw_api_kotlin_project.utils.RequestStatus
import com.sw.sw_api_kotlin_project.utils.Resource

abstract class SWApiLiveDataObserver<T> : Observer<Resource<T?>> {
    override fun onChanged(it: Resource<T?>) {
        it.let { resource ->
            when (resource.status) {
                RequestStatus.SUCCESS -> {
                    onSuccess(resource.data)
                }
                RequestStatus.ERROR -> {
                    onError(resource.message ?: "Unknown error")
                }
                RequestStatus.LOADING -> {
                    onLoading()
                }
            }
        }
    }

    abstract fun onSuccess(data: T?)
    abstract fun onError(errorMessage: String)
    open fun onLoading() {}
}