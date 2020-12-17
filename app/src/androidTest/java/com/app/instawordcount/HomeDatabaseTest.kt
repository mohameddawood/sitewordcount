package com.app.instawordcount

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.instawordcount.manager.db.Word
import com.app.instawordcount.manager.db.WordDao
import com.app.instawordcount.manager.db.WordRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class HomeDatabaseTest {
    private lateinit var wordDao: WordDao
    private lateinit var db: WordRoomDatabase
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, WordRoomDatabase::class.java
        ).build()
        wordDao = db.wordDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @After
    fun tearDown() {
        mainThreadSurrogate.close()
    }

    @Test
    @Throws(Exception::class)
    fun `insertOneWord`() = runBlocking {

        withContext(Dispatchers.IO) {
            val word = Word("OneWord", 2)
            wordDao.insert(word)
            val wordValue = wordDao.getAlphabetizedWords()
            assertThat(wordValue[0], equalTo(word))
        }

    }

    @Test
    @Throws(Exception::class)
    fun `insertListOfWord`() = runBlocking {

        withContext(Dispatchers.IO) {
            val wordsList = arrayListOf<Word>()
            wordsList.add(Word("InstaBug", 4))
            wordsList.add(Word("OneWord", 1))
            wordsList.add(Word("TwoWord", 1))
            wordsList.add(Word("ThreeWord", 4))
            wordDao.insert(*wordsList.toTypedArray())
            val allWords = wordDao.getAlphabetizedWords()
            assertThat(allWords.size, equalTo(wordsList.size))
        }
    }

    @Test
    @Throws(Exception::class)
    fun `deleteAllWords`() = runBlocking {

        withContext(Dispatchers.IO) {

            wordDao.deleteAll()
            val allWords = wordDao.getAlphabetizedWords()
            assertThat(allWords.size, equalTo(arrayListOf<WordDao>().size))
        }
    }


}