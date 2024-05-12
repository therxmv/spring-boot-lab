package com.therxmv.project8.session

import com.fasterxml.jackson.annotation.JsonProperty

data class TokenModel(
    @JsonProperty("access_token") val accessToken: String,
    @JsonProperty("token_type") val tokenType: String,
    @JsonProperty("expires_in") val expiresIn: Int,
)