package com.sw.sw_api_kotlin_project.api.client

import java.net.URI

enum class  HttpMethod {
    GET,
    POST,
    PUT,
    DELETE
}

data class HttpHeader(
    val key: String,
    val value: String?
)

interface HttpRequest {
    val httpMethod: HttpMethod
    val uri: URI
    val headers: List<HttpHeader>
    val body: String?
}

data class HttpResponse(
    val body: String?,
    val statusCode: Int
)