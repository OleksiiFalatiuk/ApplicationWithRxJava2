package com.example.testapplicationwithrxjava2.data.remote.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CryptoItemResponse(
    @SerialName("id") val id: String,
    @SerialName("price") val price: String,
    @SerialName("logo_url") val logo_url: String,
    @SerialName("rank") val rank: String,
    @SerialName("name") val name: String
)