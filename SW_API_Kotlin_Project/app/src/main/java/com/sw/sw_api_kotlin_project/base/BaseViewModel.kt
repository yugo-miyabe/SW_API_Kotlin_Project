package com.sw.sw_api_kotlin_project.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    // API通信：通信中のインジケーター
    private val _loadingEvent = MutableLiveData<Boolean>()
    val loadingEvent: LiveData<Boolean> = _loadingEvent



    /**
     * 通信中のインジケーターを表示のイベントを送信
     *
     */
    fun startLoading() {
        _loadingEvent.postValue(true)
    }

    /**
     * 通信中のインジケーターを消すイベントを送信
     */
    fun stopLoading() {
        _loadingEvent.postValue(false)
    }
}