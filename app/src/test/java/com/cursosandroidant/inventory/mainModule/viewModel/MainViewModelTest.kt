package com.cursosandroidant.inventory.mainModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
//Con esta linea de codigo nos permite especificar el api del sdk que queremos trabajar con roboelectric
//esto se hace cuando android esta en constante actualización y no va a la par con roboelectric y es necesario
//hacer esto cuando la prueba sale error y es problema de la version de android
//@Config(sdk = [21,22,30])
//@Config(maxSdk = 30)
@RunWith(AndroidJUnit4::class)
class MainViewModelTest{
    //Es necesario agregar estas lineas de codigo para poder agregar la arquitectura de componentes que se desea testear
    @get:Rule
    var instantExcecutorRule = InstantTaskExecutorRule()

    @Test
    fun checkWelcomeTest(){
        //En el viewmodel se requiere el contexto de la aplicacion y esto lo provee roboelectric
        val mainViewModel = MainViewModel(ApplicationProvider.getApplicationContext())
        val observer = Observer<Boolean>{}

        try {
            mainViewModel.isWelcome().observeForever(observer)
            val result = mainViewModel.isWelcome().value
            assertThat(result, not(nullValue()))
            assertThat(result, `is`(true))
        }finally {
            mainViewModel.isWelcome().removeObserver(observer)
        }
    }
}