package com.sw.sw_api_kotlin_project.model.entity


data class Resource<out T>(val status: RequestStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = RequestStatus.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = RequestStatus.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> =
            Resource(status = RequestStatus.LOADING, data = data, message = null)
    }
}
