package com.sw.sw_api_kotlin_project.model.entity

import androidx.lifecycle.Observer

abstract class SWLiveDataObserver<T> : Observer<Resource<T?>> {
    override fun onChanged(it: Resource<T?>) {
        it.let { resource ->
            when (resource.status) {
                RequestStatus.SUCCESS -> {
                    onSuccess(resource.data)
                    onViewChange(true)
                }
                RequestStatus.ERROR -> {
                    onError(resource.message ?: "Unknown error")
                }
                RequestStatus.LOADING -> {
                    onLoading()
                    onViewChange(false)
                }
            }
        }
    }

    abstract fun onSuccess(data: T?)
    abstract fun onError(errorMessage: String)
    open fun onLoading() {}
    open fun onViewChange(shouldListShow: Boolean) {}
}