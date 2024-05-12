package com.therxmv.project8.session

import com.therxmv.project8.utils.BaseWebClient
import com.therxmv.project8.utils.Session.AUTH_PASSWORD
import com.therxmv.project8.utils.Session.AUTH_USERNAME
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux

class SessionService : SessionApi {

    private val client: WebClient = BaseWebClient.getClient(
        withAuth = {
            this.filter(basicAuthentication(AUTH_USERNAME, AUTH_PASSWORD))
        }
    )

    override fun getAuthToken() = client
        .post()
        .uri("/oauth2/token?grant_type=client_credentials")
        .retrieve()
        .bodyToFlux<TokenModel>()
        .blockFirst()
}