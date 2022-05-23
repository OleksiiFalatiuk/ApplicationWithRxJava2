package com.example.testapplicationwithrxjava2.data.remote

import com.example.testapplicationwithrxjava2.models.CryptoMain

interface RemoteDataSource {

    suspend fun loadCrypto(): List<CryptoMain>

}