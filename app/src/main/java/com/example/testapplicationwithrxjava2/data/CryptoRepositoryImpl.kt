package com.example.testapplicationwithrxjava2.data

import com.example.testapplicationwithrxjava2.data.remote.RemoteDataSource
import com.example.testapplicationwithrxjava2.models.CryptoMain
import io.reactivex.Single
import java.util.*

class CryptoRepositoryImpl(private val remote: RemoteDataSource): CryptoRepository {

    override suspend fun loadCrypto(): List<CryptoMain> {
        return remote.loadCrypto()
    }

    override fun loadRxCrypto(): Single<List<CryptoMain>> {
        return remote.loadRxCrypto()
    }
}