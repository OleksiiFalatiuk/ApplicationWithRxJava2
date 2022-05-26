package com.example.testapplicationwithrxjava2.data.remote.retrofit

import com.example.testapplicationwithrxjava2.data.remote.RemoteDataSource
import com.example.testapplicationwithrxjava2.models.CryptoMain
import io.reactivex.Single

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

    override fun loadRxCrypto(): Single<List<CryptoMain>> {
        val cryptoRx = api.getCurrenciesRxTicker()
        return cryptoRx.map {
            it.map {
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
}