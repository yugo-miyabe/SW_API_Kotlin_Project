package com.sw.sw_api_kotlin_project.utils

import com.sw.sw_api_kotlin_project.api.APIErrorType
import com.sw.sw_api_kotlin_project.model.error.APIError

sealed class Result<out R> {
    /**
     * 成功時
     *
     * @param T データタイプ
     * @property data データ
     */
    data class Success<out T>(val data: T) : Result<T>()

    /**
     * 失敗時
     *
     * @property error サーバーのレスポンス
     **/
    data class Error(
        val error: APIError
    ) : Result<Nothing>() {
        var type: APIErrorType = APIErrorType.ServerError

        init {
            type = when (error.detail) {
                "Not found" -> APIErrorType.NotFound
                else -> APIErrorType.ServerError
            }
        }
    }

    object Loading : Result<Nothing>()
}



