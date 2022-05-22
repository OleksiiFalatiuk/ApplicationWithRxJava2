package com.example.testapplicationwithrxjava2.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplicationwithrxjava2.data.CryptoRepository
import com.example.testapplicationwithrxjava2.models.CryptoMain
import kotlinx.coroutines.launch


class CryptoMainViewModel(private val repository: CryptoRepository): ViewModel() {

    private val _cryptoLiveData = MutableLiveData<CryptoMain>(null)

    val cryptoLiveData: LiveData<CryptoMain> = _cryptoLiveData

    init {
        loadCryptoMainInfo()
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

}