package com.cursosandroidant.inventory.addModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cursosandroidant.inventory.entities.Product
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddViewModelTest{
    @get:Rule
    var instantExcecutorRule = InstantTaskExecutorRule()

    @Test
    fun addProductTest(){
        val addViewModel = AddViewModel()
        val product = Product(117,"XBOX", 20,
        "https://www.trustedreviews.com/wp-content/uploads/sites/54/2020/10/Xbox-Series-S-34-scaled-e1603883732720-1220x812.jpg",
        4.8, 56)
        //Creamos el observador de tipo Boolean, ya que la variable que contiene la información es de tipo booleano y es lo que va a observar
        //proviene de esta linea de codigo: private val result = MutableLiveData<Boolean>()
        val observer = Observer<Boolean>{}

        //Tenemos que evaluar el observador dentro de un try finally ya que una vez que se ejecuta el observer lo teneomos que finalizar
        //y para finalizarlo necesitamos ejecutar el codigo en finally para terminar el proceso
        try {
            //aqui ya ponemos a la escucha del valor que va a estar observando o suscrito el observer, es como decir
            //esto viewModel.getResult().observe(viewLifecycleOwner, { result ->
            addViewModel.getResult().observeForever(observer)
            //Aqui le pasamos el producto que se va añadir
            addViewModel.addProduct(product)
            //aqui lo que hacemos es obtener el valor despues de haber agregado el producto, es decir si se agrego un producto
            //el valor esperado es un true
            val result = addViewModel.getResult().value
            //el resultado es nuestro valor actual y true es el valor esperado
            assertThat(result, `is`(true))
            //assertThat(result, not(nullValue()))
        }finally {
            //aqui ya removemos el observador
            addViewModel.getResult().removeObserver(observer)
        }
    }
}