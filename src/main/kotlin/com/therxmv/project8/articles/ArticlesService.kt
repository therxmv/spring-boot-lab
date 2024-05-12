package com.therxmv.project8.articles

import com.therxmv.project8.session.SessionApi
import com.therxmv.project8.session.SessionService
import com.therxmv.project8.utils.BaseWebClient
import org.springframework.http.HttpHeaders
import org.springframework.web.reactive.function.client.bodyToFlux

class ArticlesService(
    private val sessionApi: SessionApi = SessionService(),
) : ArticlesApi {

    private val client = BaseWebClient.getClient(
        withAuth = {
            this@getClient.defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer ${getToken()}")
        }
    )

    override fun getArticles() = client
        .get()
        .uri("/articles")
        .retrieve()
        .bodyToFlux<String>()
        .blockFirst()

    private fun getToken() = sessionApi.getAuthToken()?.accessToken.orEmpty()
}