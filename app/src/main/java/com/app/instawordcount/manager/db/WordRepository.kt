package com.app.instawordcount.manager.db

import androidx.annotation.WorkerThread

class WordRepository(private val wordDao: WordDao) {


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(vararg word: Word) {
        wordDao.insert(*word)
    }

    fun getAllWords(): List<Word> {
        return wordDao.getAlphabetizedWords()
    }
}