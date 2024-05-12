package com.therxmv.project8.time

import com.therxmv.project8.session.SessionApi
import com.therxmv.project8.session.SessionService
import com.therxmv.project8.utils.BaseWebClient
import org.springframework.http.HttpHeaders
import org.springframework.web.reactive.function.client.bodyToMono

class TimeService(
    private val sessionApi: SessionApi = SessionService(),
) : TimeApi {

    private val client = BaseWebClient.getClient(
        withAuth = {
            this@getClient.defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer ${getToken()}")
        }
    )

    override fun getTime() = client
        .get()
        .uri("/api/v1/common/time")
        .retrieve()
        .bodyToMono<String>()
        .block()

    private fun getToken() = sessionApi.getAuthToken()?.accessToken.orEmpty()
}