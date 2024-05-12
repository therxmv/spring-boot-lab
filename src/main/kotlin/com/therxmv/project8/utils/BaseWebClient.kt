package com.therxmv.project8.utils

import com.therxmv.project8.utils.Session.BASE_URL
import org.springframework.web.reactive.function.client.WebClient

object BaseWebClient {
    private var client: WebClient.Builder? = null

    fun getClient(withAuth: WebClient.Builder.() -> WebClient.Builder): WebClient {
        if (client == null) {
            client = WebClient.builder()
                .baseUrl(BASE_URL)
        }

        return client!!
            .withAuth()
            .build()
    }
}