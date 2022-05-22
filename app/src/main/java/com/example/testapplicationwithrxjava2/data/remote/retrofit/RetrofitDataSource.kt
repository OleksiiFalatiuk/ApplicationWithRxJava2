package com.example.testapplicationwithrxjava2.data.remote.retrofit

import com.example.testapplicationwithrxjava2.data.remote.RemoteDataSource
import com.example.testapplicationwithrxjava2.models.CryptoMain

class RetrofitDataSource(private val api: CryptoApiService): RemoteDataSource {

    override suspend fun loadCrypto(): CryptoMain {
        val crypto = api.getCurrenciesTicker()
        return CryptoMain(
            id = crypto.id,
            name = crypto.name,
            rank = crypto.rank,
            price = crypto.price,
            logo_url = crypto.logo_url
        )
    }
}