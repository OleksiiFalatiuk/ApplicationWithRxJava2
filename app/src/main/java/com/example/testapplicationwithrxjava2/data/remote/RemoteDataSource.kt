package com.example.testapplicationwithrxjava2.data.remote

import com.example.testapplicationwithrxjava2.models.CryptoMain
import io.reactivex.Single

interface RemoteDataSource {

    suspend fun loadCrypto(): List<CryptoMain>

    fun loadRxCrypto(): Single<List<CryptoMain>>

}