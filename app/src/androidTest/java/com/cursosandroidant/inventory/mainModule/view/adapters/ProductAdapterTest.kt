package com.cursosandroidant.inventory.mainModule.view.adapters

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.cursosandroidant.inventory.mainModule.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.cursosandroidant.inventory.R
import com.cursosandroidant.inventory.clickInChild
import org.hamcrest.Matchers.*
import org.junit.Assert.fail

@LargeTest
@RunWith(AndroidJUnit4::class)
class ProductAdapterTest{

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun listItem_click_successCheck(){
        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<ProductAdapter.ViewHolder>(1, click()))

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Queso")))
    }

    //Es necesario utilizar hamcrest en nuestras pruebas para poder tener mayor flexibilidad en los resultados esperados
    //y en la lectura de la prueba
    @Test
    fun listItem_longClick_removeFromView(){
        onView(withId(R.id.recyclerView))
            .perform(
                actionOnItem<ProductAdapter.ViewHolder>(
                hasDescendant(withText(containsString("Tijeras"))), longClick() //Estamos forzando que haga scroll hasta que encuentre el elemento tijeras
            ),
                scrollTo<ProductAdapter.ViewHolder>(
                    hasDescendant(withText(containsString("Vino")))
                )
            )
        //Es solo para dar tiempo de ver la acción que realiza android studio
        //Thread.sleep(1_000)


        try {
            onView(withId(R.id.recyclerView))
                .perform(
                    scrollTo<ProductAdapter.ViewHolder>(
                    hasDescendant(withText(containsString("Tijeras")))
                ))

            fail("Tijeras aun existe!!") //Forzando un error para que entre al catch
        } catch (e: Exception) {
            assertThat((e as? PerformException), `is`(notNullValue()))
        }
    }

    @Test
    fun cbFavorite_click_changeState(){

        onView(withId(R.id.recyclerView))
            .perform(
                actionOnItemAtPosition<ProductAdapter.ViewHolder>(
                1, clickInChild(R.id.cbFavorite)
            ))
        //Thread.sleep(1_000)
    }



}