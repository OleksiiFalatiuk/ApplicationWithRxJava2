package com.example.testapplicationwithrxjava2.data

import com.example.testapplicationwithrxjava2.data.remote.RemoteDataSource
import com.example.testapplicationwithrxjava2.models.CryptoMain
import io.reactivex.rxjava3.core.Observable
import java.util.*

class CryptoRepositoryImpl(private val remote: RemoteDataSource): CryptoRepository {

    override suspend fun loadCrypto(): CryptoMain {
        return remote.loadCrypto()
    }
}