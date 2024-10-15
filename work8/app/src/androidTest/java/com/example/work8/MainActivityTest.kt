package com.example.work8

import android.os.SystemClock.sleep
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.containsString
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

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
    fun errorDownload(){
        onView(withId(R.id.editTextText)).perform(typeText("rrr"), closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        sleep(5000)
        // Утверждение о наличии Toast
        onView(withText(containsString("Неверная ссылка")))
            .check(matches(isDisplayed()))
    }
    @Test
    fun normalDownload(){
        onView(withId(R.id.editTextText)).perform(typeText("https://kinopoisk-ru.clstorage.net/146w2wo17/1a67a2nc8FKy/KaQHs_VgJilwoG-Ov_IE5RkMXWcGjBrKsX7ZjYNE1SjiDMsmRXoIVg_zlyUz79wmEfkyou3xu7_Y8n7IrTSWlwjxAkLUpaYMkdHf_n_iBURs6Rxz0gkui2BL3Ghxt9erwJvpRbf4CpAbl4NU1joJshCsQiPIMM8Ov9SPvdlxMKEIZGYDsUGnWrajz41fUzQxeJ59cWTead4j7OkfkBUVKKHIWjUU6ksAOhaAktWY6eLy_MOjaJY-_Oyk-e6o4KHzmGTk45Pz9-s1w8z8P1DWE6mvDyF37Us-M5zJz-EmVahSCDukhbqbounUgRC22LpQAIxiAog3PF0etwlOevCDMXr1JAPgoobYxiNc7X3yBOGZPT7ThD3aLFKeWx0wNsd60atL1DXJOnJ4lXLTlbvqQpNuAMH4Rn2tTwbqvTpAAFK5t3fCE8GUepUA326twDTxmP-vA7bsaU3yn8s8QzcVKyMrOSWlKSqyOsXTMwYYGzGCDxMw6CTO3r1UC13okKDQW4eWcDNR9JlE4U6cn4F28ytdnUL3zlkes_yqbYHW96miGjok5fiZoao20yGXKxuyEL5ic3rVLS8vJfvuWzDDIBq1BsOjEYYqBQGPzh0i9mCpbE1DBg1ZLgLM678hJVeZcbhZRqW6qyJ4BhCjJxi7kXH-UoPYVJ2vXHf4rLuSkkKYVRRCQ-B0iGZjfh49MHYzed884aYeKU8wvyl_AQSnKlKaW5cF61rQWmbSEtQI2DCzbSGja5e8Xa2GeU46QjODGAaW8NEih2tXUty8PyKH0OkeT4OUTlrtAt6KXOL3tOhRGIi09lgacWnksKD3imhzQ-8z4nmXPTx9B-lPSHFicBqkpYLSs5f7FgHt_D1yR8CpTE2g9Lxrn3DNOU1hBAaJsgkZJuRKGTH4haGg57qp8qHccCLbBB7ujPT6L4qy0QEZZMRBUoF069TC7y4dcHQweL6PIpevGC3ijhh9AmY260Eq26ZVu2oiCGRioyeYO5PCreOSGwZObrxVurwLghDgekSEoADzpKnmES88bhB2UWmsXWO1fQqeAq6J7EDW51tgKKq2NEr4ciimgTG3qMhAAQygwYmE_K8e51kdO2PAQWt2JHPzk9VahNEOnB3Q1iLrHS5Dxu5Yz1Oce-_DNjULgit6FZUZaSHIlbOgVMnaMPMvk9FptBw8jLWpH0jTYQIrtyQRsKIFOMTQXD6Mk9cRCGy9Aeduit_hbHpt89amixEqyqR16pkwCGfjMoQaegIh_nDzm4dPPK-16P6q8iBAiJU2YhOR1gp0sX7c7_EmE4ktDBFn_3tuoa9ZfdLXpIry-qlWdEta0tt0wmB2C8oxwI-CwAmHj0yct7iPeBLjsrhkBHJgEoVI9-O9v94ih6No_E5T5D8LzBHeOS0QpwXpY4jrBlT4iJPp56BilqvJIlCfgbAaNk7PXjcbfGsQ4uDpRgWgcCH3agfgjB8PEQWS6J0M0yQuexzxnQkuMBQ16zH6efanimrR6aexMLWpO6Bx3DLTidcsbL4GKYx5c8ERCATlwuIRlPll4I0cPUMEUWhObUG3jimt4yxZj2FnN8kjyFkVtikq0EjH0UB0mbowA27wIHkXbO0cFWv9mIIjcvtnBqAzs1RKh-KNzUzgRwLa_F6zlr963HMPa03w1FSok8q5dbZoWTCJ9tDwRPm7slIvgGPKZw6_3RfK7_ozM7J5x3RBY0PUqsbi3KxsEAUTuay_UyTsm17QzOuc8hTle4CoC8b3qUjyelewMOUo28PCLJDQahRMXM5HqR14wCDSWgb0YrMgRQnnEz6vTfCFwppM3EC2Lsjeka9oTgEHN6lx6qvVNRjJM3p2kyD0SuvCoAxgAcqm7OyM98mdWqCjAvgERPNDUtSqpmDMvk1BBBPIby7hNJ6KDiKtOT7hJXXrI8iL19X56XGIl2Njdwgpc4N9EEH6BA0tPFT57bjjY7AKhEQyk6CkKVSCvX1NIzQjuoxPg-dcGczxnimfktQEGZOYytZViijyaZZhE5QJqzNw7fDyS9acjq43u3xqYFORSKbkQEDCRlqEU79ubeP18Xqe_2GkLgq-o_yZreEXBpnCimk0xYsLkEikAtCESRkQUbzSo4i0ft6O1Yksi4KzwvuVtsPhQYSr59CPXOwi9bCZPX1i5c9Y_DG9OyxDNrWqcMsrBHd5GsHaJMKjF4mIQiMuUdHLts6c3Pfpf4kwETKbdgYTsALn-qZy7qxPc1TDe4y-49Te6xzRnJoeUcS0mrHLePTk-uhAWPWiQoTpmNKAzoAgO8dejO61OdyKkOKhePRXg6IgpFpXUe_M74PFstjNnoL1bemeMy6qPkNHpRsRmirGR_q6gmlFkUGV-doRko2xkApnjG3cB-l-WIJywFumtMFiEfVLBrKsvq3gFBCITC6C1N9LbLBve02xFzT6wyqqxFcrO2O41XMzJLnJEWF8AdB59U2-bLeZziiDMXD6l1aiAaJGyjQBDq4egScQa95O0eY9eJ0h3BpdwMcGmcJqOiZ3-GuDWbRSkAW5u0LynlLi2Pe9raz1qS464yGiu6T08jGSVErHAz3s_0PGIGs8rPCWHFjeQL1ZjeE3Z5mxGPvWpxhYcqj0U1K36NryosySc4qXXl0fRws-OwHDIqlnVmPQs6UYtMOMvE3yBzJZH30itozJL-E8m72Adgd74FsZhqRIyRNq1fFSxHi7MgEfMuOKJFzuTkcLr1rg8nKLRHYRwQOlOvTRfN4ssDRzCc-coPQM-11B3-u84seHK5H42KYVKCiim4WQAuT42sBy7TPTyiWOfn20O2_qoQEyWBVngvOwxQin0owMzPKlQStuTyJ2HposoF4rfcNERWkgmOqWBxhJQnpko2N2CavRsT7yUuuVbW_PV_n8WyFzQAu1RZPAkif6BdNcP89BNfK7jl2BFl7JnCNt6C8AFDTbAIjZNOfq2hCaF3EzdYvZs5H98qIqxm6fg"),
            closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        sleep(5000)
        // Утверждение о наличии Toast
        onView(withText(containsString("Изображение загружено")))
            .check(matches(isDisplayed()))
    }



}