package com.example.work8

import android.os.SystemClock.sleep
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {


    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun onCreate(){
        //Проверка видимости
        onView(withId(R.id.button)).check(matches(isDisplayed()))
        onView(withId(R.id.imageView)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextText)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewStatus)).check(matches((not(isDisplayed()))))
    }

    @Test
    fun editTextTextTest(){
        //Ввод значения
        onView(withId(R.id.editTextText)).perform(typeText("Hello"), closeSoftKeyboard())
    }

    @Test
    fun  defaultImageBtn(){
        onView(withId(R.id.button)).perform(click())
        sleep(5000)
        onView(withId(R.id.imageView)).check(matches(isDisplayed()))
    }

    @Test
    fun ErrorDownload(){
        onView(withId(R.id.editTextText)).perform(typeText("rrr"), closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        sleep(800)
        onView(withId(R.id.textViewStatus)).check(matches(isDisplayed()))
    }

    @Test
    fun GoodDownload(){
        onView(withId(R.id.editTextText)).perform(typeText("https://avatars.mds.yandex.net/get-kinopoisk-image/1600647/52548a69-37d6-44e7-829f-65f0eb9facfc/1920x"), closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        sleep(800)
        onView(withId(R.id.imageView)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewStatus)).check(matches(not(isDisplayed())))
    }



}