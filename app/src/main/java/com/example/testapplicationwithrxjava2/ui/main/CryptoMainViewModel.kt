package com.example.testapplicationwithrxjava2.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplicationwithrxjava2.data.CryptoRepository
import com.example.testapplicationwithrxjava2.models.CryptoMain
import com.example.testapplicationwithrxjava2.utils.debugDo
import com.example.testapplicationwithrxjava2.utils.logError
import com.example.testapplicationwithrxjava2.utils.logErrorStackTrace
import com.example.testapplicationwithrxjava2.utils.logInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CryptoMainViewModel(
//    private val repository: CryptoRepository
    private val getCrypto: () -> Flow<List<CryptoMain>>,
    private val getCryptoCoroutines: suspend () -> List<CryptoMain>
) : ViewModel() {

    private val _cryptoLiveData = MutableLiveData<List<CryptoMain>>(null)

    val cryptoLiveData: LiveData<List<CryptoMain>> = _cryptoLiveData

    private val disposable = CompositeDisposable()

    init {
        loadCryptoWithFlowRepeat()
//        loadRxCrypto()
//        Log.d("checkData", "start")
        kotlin.runCatching {
            10 / 0
        }.getOrElse {
            logInfo("error")
            it.logError()
            it.logErrorStackTrace()
            debugDo(false) {

            }
        }
    }

    fun loadCryptoMainInfo() {
        viewModelScope.launch {
            try {
                _cryptoLiveData.value = getCryptoCoroutines.invoke()
            } catch (ex: Exception) {
                ex.printStackTrace()
                _cryptoLiveData.value = emptyList()
            }
        }
    }

    private fun loadCryptoWithFlowRepeat() {
        getCrypto.invoke()
            .onEach {
                _cryptoLiveData.value = it
            }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope)
    }


    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

//    private fun loadRxCrypto() {
//        val result = repository.loadRxCrypto()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                    _cryptoLiveData.value = it
//                    Log.d("checkData", "all is success")
//                },
//                {
//                    Log.d("checkData", it.stackTraceToString())
//                }
//            )
//        disposable.add(result)
//    }

}