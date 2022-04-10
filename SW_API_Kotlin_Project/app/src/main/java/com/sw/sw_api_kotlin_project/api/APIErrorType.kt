package com.sw.sw_api_kotlin_project.api

enum class APIErrorType {
    /** ネットワーク異常 */
    NetworkError,

    /** ネットワーク設定無効 */
    NetworkNotConnectError,

    /** 想定していないエラー */
    ServerError,

    /** 上記以外 */
    OtherError,
}