package com.cursosandroidant.inventory.mainModule.view

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.cursosandroidant.inventory.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun actionBar_menuItemClick_returnsMsg(){
        //Se va a presionar primero el recycler view pero no en cada elemento que tenga dentro del recyclerview
        //se hace esto por que se recomienda para ciertos escenarios que a lo mejor tenemos un menu flotante
        //o cualquier cosa q pueda interrumpir nuestra interfaz, entonces hacemos esto para disipar
        //algun menu flotante y despues ejecutar lo queremos testear.
        onView(withId(R.id.recyclerView)).perform(click())

        onView(withId(R.id.action_history)).perform(click())

        //Esto es para ver el string para multi idiomas
        var snackbarMsg = ""
        activityScenarioRule.scenario.onActivity { activity ->
            snackbarMsg = activity.resources.getString(R.string.main_msg_go_history)
        }

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(snackbarMsg)))

    }

    @Test
    fun contextMenu_menuItemClick_returnsMsg(){
        onView(withId(R.id.recyclerView)).perform(click())

        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext())

        onView(withText("Salir")).perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Salir…")))
    }
}
