package com.example.testapplicationwithrxjava2.di

import com.example.testapplicationwithrxjava2.data.CryptoRepository
import com.example.testapplicationwithrxjava2.data.CryptoRepositoryImpl
import com.example.testapplicationwithrxjava2.data.remote.NetworkModule
import com.example.testapplicationwithrxjava2.data.remote.RemoteDataSource
import com.example.testapplicationwithrxjava2.data.remote.retrofit.CryptoApiService
import com.example.testapplicationwithrxjava2.data.remote.retrofit.RetrofitDataSource
import com.example.testapplicationwithrxjava2.ui.main.CryptoMainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val cryptoModule = mutableListOf<Module>(
    module {

        factory<CryptoApiService> {
            NetworkModule().api
        }

        single<RemoteDataSource> {
            val api = get<CryptoApiService>()
            RetrofitDataSource(
//            api = get<CryptoApiService>()
                getListApi = api::getCurrenciesTicker,
                getSingleApi = api::getCurrenciesRxTicker
            )
        }

        single<CryptoRepository> {
            val remote = get<RemoteDataSource>()
            CryptoRepositoryImpl(
//            remote = get<RemoteDataSource>()
                getRemote = remote::loadRepeatCryptoRx,
                getRemoteList = remote::loadCrypto,
                getRemoteListRx = remote::loadRxCrypto
            )
        }

        viewModel<CryptoMainViewModel> {
            val repository = get<CryptoRepository>()
            CryptoMainViewModel(
//            repository = get<CryptoRepository>()
                getCrypto = repository::loadRepeatCryptoRx,
                getCryptoCoroutines = repository::loadCrypto
            )
        }

    }
)

