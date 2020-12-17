package com.app.instawordcount.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.instawordcount.di.viewModelModule
import junit.framework.Assert.assertEquals
import org.junit.*
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.MockitoAnnotations

class HomeViewModelTest : KoinTest {
    val homeViewModel: HomeViewModel by inject()
    val repository: HomeRepository by inject()

    val emptySiteWords = ""
    val siteWords =
        "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc,\n"

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin {
            modules(viewModelModule)
        }
    }

    @Test
    fun `observe progress loading`() {
        homeViewModel.showLoadingProcess.value = true
        homeViewModel.showLoadingProcess.observeForever {
            Assert.assertEquals(true, it)
        }
    }

    @Test
    fun `observe connection sheet not showing`() {
        homeViewModel.showNoConnection.observeForever {
            Assert.assertEquals(false, it)
        }
    }

    @Test
    fun `observe progress not loading`() {
        homeViewModel.showLoadingProcess.value = false
        homeViewModel.showLoadingProcess.observeForever {
            Assert.assertEquals(false, it)
        }
    }

    @Test
    fun `site words empty return Nothing`() {
        homeViewModel.setWordsMap(emptySiteWords)
        homeViewModel.observeWords.observeForever {
            assertEquals("", it)
        }
    }

    @Test
    fun `site words return map of wordsCount`() {
        homeViewModel.setWordsMap(siteWords)
        homeViewModel.observeWords.observeForever {
            assertEquals(hashMapOf<String, Int>(), it)
        }
    }

    @After
    fun after() {
        stopKoin()
    }

}