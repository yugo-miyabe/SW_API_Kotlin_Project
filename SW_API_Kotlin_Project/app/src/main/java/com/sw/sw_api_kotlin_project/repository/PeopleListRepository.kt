package com.sw.sw_api_kotlin_project.repository

import com.sw.sw_api_kotlin_project.api.client.HttpHeader
import com.sw.sw_api_kotlin_project.api.client.HttpMethod
import com.sw.sw_api_kotlin_project.api.client.HttpRequest
import java.net.URI

class PeopleListRepository(
    override val httpMethod: HttpMethod,
    override val uri: URI,
    override val headers: List<HttpHeader>,
    override val body: String?
) : HttpRequest





