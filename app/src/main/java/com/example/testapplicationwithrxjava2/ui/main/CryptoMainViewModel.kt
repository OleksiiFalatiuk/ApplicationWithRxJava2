package com.example.testapplicationwithrxjava2.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplicationwithrxjava2.data.CryptoRepository
import com.example.testapplicationwithrxjava2.models.CryptoMain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class CryptoMainViewModel(private val repository: CryptoRepository): ViewModel() {

    private val _cryptoLiveData = MutableLiveData<List<CryptoMain>>(null)

    val cryptoLiveData: LiveData<List<CryptoMain>> = _cryptoLiveData

    private val disposable = CompositeDisposable()

    init {
//        loadCryptoMainInfo()
        loadRxCrypto()
        Log.d("checkData", "start")
    }

    private fun loadCryptoMainInfo(){
        viewModelScope.launch {
            try {
                _cryptoLiveData.value = repository.loadCrypto()
            }catch (ex: Exception){
                ex.printStackTrace()
            }
        }
    }



    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    private fun loadRxCrypto(){
        val result = repository.loadRxCrypto()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _cryptoLiveData.value = it
                    Log.d("checkData", "all is success")
                },
                {
                    Log.d("checkData", it.stackTraceToString())
                }
            )
        disposable.add(result)
    }

}