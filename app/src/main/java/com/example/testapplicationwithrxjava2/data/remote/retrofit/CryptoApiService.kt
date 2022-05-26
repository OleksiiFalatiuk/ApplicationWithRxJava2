package com.example.testapplicationwithrxjava2.data.remote.retrofit

import com.example.testapplicationwithrxjava2.data.remote.retrofit.response.CryptoItemResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CryptoApiService {

    @GET("currencies/ticker")
    suspend fun getCurrenciesTicker(): List<CryptoItemResponse>

    @GET("currencies/ticker")
    fun getCurrenciesRxTicker(): Single<List<CryptoItemResponse>>
}