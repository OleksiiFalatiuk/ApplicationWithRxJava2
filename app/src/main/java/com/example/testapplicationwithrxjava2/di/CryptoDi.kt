package com.example.testapplicationwithrxjava2.di

import com.example.testapplicationwithrxjava2.data.CryptoRepository
import com.example.testapplicationwithrxjava2.data.CryptoRepositoryImpl
import com.example.testapplicationwithrxjava2.data.remote.NetworkModule
import com.example.testapplicationwithrxjava2.data.remote.RemoteDataSource
import com.example.testapplicationwithrxjava2.data.remote.retrofit.CryptoApiService
import com.example.testapplicationwithrxjava2.data.remote.retrofit.RetrofitDataSource
import com.example.testapplicationwithrxjava2.ui.main.CryptoMainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val cryptoModule = module {

    factory <CryptoApiService> {
        NetworkModule().api
    }

    single<RemoteDataSource> {
        RetrofitDataSource(
            api = get<CryptoApiService>()
        )
    }

    single<CryptoRepository> {
        CryptoRepositoryImpl(
            remote = get<RemoteDataSource>()
        )
    }

    viewModel <CryptoMainViewModel> {
        CryptoMainViewModel(
            repository = get<CryptoRepository>()
        )
    }

}