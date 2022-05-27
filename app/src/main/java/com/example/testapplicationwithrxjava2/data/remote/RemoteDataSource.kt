package com.example.testapplicationwithrxjava2.data.remote

import com.example.testapplicationwithrxjava2.models.CryptoMain
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun loadCrypto(): List<CryptoMain>

    fun loadRxCrypto(): Single<List<CryptoMain>>

//    fun loadRepeatCryptoRx(): Observable<List<CryptoMain>>

    fun loadRepeatCryptoRx(): Flow<List<CryptoMain>>

}