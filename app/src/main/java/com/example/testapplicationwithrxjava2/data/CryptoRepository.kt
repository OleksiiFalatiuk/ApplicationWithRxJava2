package com.example.testapplicationwithrxjava2.data

import com.example.testapplicationwithrxjava2.models.CryptoMain
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {

    suspend fun loadCrypto(): List<CryptoMain>

    fun loadRxCrypto(): Single<List<CryptoMain>>

    fun loadRepeatCryptoRx(): Flow<List<CryptoMain>>

}