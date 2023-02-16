package com.cursosandroidant.inventory

import android.view.View
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.UiController
import org.hamcrest.Matcher

//Esto es para acceder al hijo de un elemento del recyclerView
fun clickInChild(id: Int): ViewAction {
    return object : ViewAction{
        override fun getConstraints(): Matcher<View> = withId(id)

        override fun getDescription(): String = "Child in ViewHolder"

        override fun perform(uiController: UiController?, view: View) {
            (view.findViewById(id) as View).performClick()
        }
    }
}