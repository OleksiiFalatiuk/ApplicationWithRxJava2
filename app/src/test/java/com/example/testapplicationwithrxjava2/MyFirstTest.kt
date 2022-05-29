package com.example.testapplicationwithrxjava2

import com.example.testapplicationwithrxjava2.data.CryptoRepository
import com.example.testapplicationwithrxjava2.models.CryptoMain
import com.example.testapplicationwithrxjava2.ui.main.CryptoMainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class MyFirstTest: KoinTestClassJunit() {

    @Test
    fun myFirstTest(){
        val module = module {
            viewModel<CryptoMainViewModel> {
                val repository = get<CryptoRepository>()
                CryptoMainViewModel(
                    getCrypto = { flow<List<CryptoMain>> {  } },
                    getCryptoCoroutines = { listOf(
                        CryptoMain(
                            id = "1",
                            logo_url = "sdcsdc",
                            name = "Saaaa",
                            price = "123",
                            rank = "1"
                        ),
                        CryptoMain(
                            id = "2",
                            logo_url = "sdcsdc",
                            name = "Saaaa",
                            price = "123",
                            rank = "1"
                        ),
                        CryptoMain(
                            id = "3",
                            logo_url = "sdcsdc",
                            name = "Saaaa",
                            price = "123",
                            rank = "1"
                        )
                    ) }
                )
            }
        }
        loadKoinModules(module)

        val vm by inject<CryptoMainViewModel>()
        vm.loadCryptoMainInfo()
        val result = vm.cryptoLiveData.value
        assertEquals(result?.size, 3)
        unloadKoinModules(module)
    }

    @Test
    fun mySecondTest(){
        val module = module {
            viewModel<CryptoMainViewModel> {
                val repository = get<CryptoRepository>()
                CryptoMainViewModel(
                    getCrypto = { flow<List<CryptoMain>> {  } },
                    getCryptoCoroutines = {
                        throw Exception("some error")
                    }
                )
            }
        }
        loadKoinModules(module)

        val vm by inject<CryptoMainViewModel>()
        vm.loadCryptoMainInfo()
        val result = vm.cryptoLiveData.value
        assertEquals(result, emptyList<CryptoMain>())
        unloadKoinModules(module)
    }

}