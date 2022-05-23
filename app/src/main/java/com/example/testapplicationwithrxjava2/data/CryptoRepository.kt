package com.example.testapplicationwithrxjava2.data

import com.example.testapplicationwithrxjava2.models.CryptoMain

interface CryptoRepository {

    suspend fun loadCrypto(): List<CryptoMain>

}