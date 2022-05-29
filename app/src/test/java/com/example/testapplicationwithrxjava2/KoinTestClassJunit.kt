package com.example.testapplicationwithrxjava2

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testapplicationwithrxjava2.di.cryptoModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.test.KoinTest
import org.mockito.Mockito


@ExperimentalCoroutinesApi
open class KoinTestClassJunit(
    private val overrideModules: List<Module> = emptyList()
): KoinTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun before() {
        cryptoModule.addAll(overrideModules)
        Dispatchers.setMain(testDispatcher)
        startKoin {
            androidContext(Mockito.mock(Application::class.java))
            modules(cryptoModule)
            allowOverride(true)
        }
    }




    @After
    fun after() {
        stopKoin()
        cryptoModule.removeAll(overrideModules)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}