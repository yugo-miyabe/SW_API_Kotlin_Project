package com.sw.sw_api_kotlin_project.data.utils

import timber.log.Timber

object LogUtils {

    /**
     * ログ出力
     */
    fun <T> timberTrace(instance: Class<T>) {
        Timber.v(
            "%s: %s",
            instance.simpleName,
            Thread.currentThread().stackTrace[2].methodName
        )
    }
}
