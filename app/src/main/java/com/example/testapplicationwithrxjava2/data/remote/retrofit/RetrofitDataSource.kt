package com.example.testapplicationwithrxjava2.data.remote.retrofit

import com.example.testapplicationwithrxjava2.data.remote.RemoteDataSource
import com.example.testapplicationwithrxjava2.models.CryptoMain

class RetrofitDataSource(private val api: CryptoApiService): RemoteDataSource {

    override suspend fun loadCrypto(): List<CryptoMain> {
        val crypto = api.getCurrenciesTicker()
        return crypto.map {
            CryptoMain(
                id = it.id,
                logo_url = it.logo_url,
                name = it.name,
                rank = it.rank,
                price = it.price
            )
        }
    }
}