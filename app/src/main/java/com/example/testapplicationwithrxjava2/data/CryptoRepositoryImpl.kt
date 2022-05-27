package com.example.testapplicationwithrxjava2.data

import com.example.testapplicationwithrxjava2.data.remote.RemoteDataSource
import com.example.testapplicationwithrxjava2.models.CryptoMain
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import java.util.*

class CryptoRepositoryImpl(
//    private val remote: RemoteDataSource
private val getRemote: () -> Flow<List<CryptoMain>>,
private val getRemoteList: suspend () -> List<CryptoMain>,
private val getRemoteListRx: () -> Single<List<CryptoMain>>
): CryptoRepository {

    override suspend fun loadCrypto(): List<CryptoMain> {
//        return remote.loadCrypto()
        return getRemoteList.invoke()
    }

    override fun loadRxCrypto(): Single<List<CryptoMain>> {
//        return remote.loadRxCrypto()
        return getRemoteListRx.invoke()
    }

    override fun loadRepeatCryptoRx(): Flow<List<CryptoMain>> {
        return getRemote.invoke()
//        return remote.loadRepeatCryptoRx()
    }
}