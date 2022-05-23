package com.example.testapplicationwithrxjava2.data.remote.retrofit.response

import com.google.gson.annotations.SerializedName



class CryptoItemResponse(
    @SerializedName("id") val id: String,
    @SerializedName("price") val price: String,
    @SerializedName("logo_url") val logo_url: String,
    @SerializedName("rank") val rank: String,
    @SerializedName("name") val name: String
)