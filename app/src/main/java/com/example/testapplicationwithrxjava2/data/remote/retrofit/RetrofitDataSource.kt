package com.example.testapplicationwithrxjava2.data.remote.retrofit

import com.example.testapplicationwithrxjava2.data.remote.RemoteDataSource
import com.example.testapplicationwithrxjava2.data.remote.retrofit.response.CryptoItemResponse
import com.example.testapplicationwithrxjava2.models.CryptoMain
import com.example.testapplicationwithrxjava2.utils.logInfo
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

class RetrofitDataSource(
//    private val api: CryptoApiService
private val getListApi: suspend () -> List<CryptoItemResponse>,
private val getSingleApi:  () -> Single<List<CryptoItemResponse>>
    ) : RemoteDataSource {

    override suspend fun loadCrypto(): List<CryptoMain> {
//        val crypto = api.getCurrenciesTicker()
        val cryptoApi = getListApi.invoke()
        return cryptoApi.map {
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
//        val cryptoRx = api.getCurrenciesRxTicker()
        val cryptoRxApi = getSingleApi.invoke()
        return cryptoRxApi.map {
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

    override fun loadRepeatCryptoRx(): Flow<List<CryptoMain>> {
        return flow {
            while (true) {
                val load = loadCrypto()
                logInfo("Success")
                emit(load)
                delay(2000)
            }
        }
            .flowOn(Dispatchers.IO)
    }

//    override fun loadRepeatCryptoRx(): Observable<List<CryptoMain>> {
//        val request = api.getCurrenciesRxTicker()
//        return request.retryWhen {
//
//            }
//
//    }
}