package com.app.instawordcount

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.app.instawordcount.ui.MainActivity
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeUiTest {

    @Test
    fun recyclerviewTest() {
        val activity = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.rv_words_count)).check(matches(isDisplayingAtLeast(1)))
    }

}


