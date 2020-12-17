package com.app.instawordcount.ui.home

import android.text.Html
import androidx.core.text.HtmlCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.instawordcount.BuildConfig
import com.app.instawordcount.manager.base.BaseViewModel
import com.app.instawordcount.manager.db.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.arrayListOf
import kotlin.collections.isNullOrEmpty
import kotlin.collections.map
import kotlin.collections.set
import kotlin.collections.toTypedArray


class HomeViewModel : BaseViewModel(), KoinComponent {
    // repo
    private val repository: HomeRepository by inject()

    // words count data
    var wordsMap: HashMap<String, Int> = HashMap()
    private var _observeWords = MutableLiveData<HashMap<String, Int>>()
    val observeWords: LiveData<HashMap<String, Int>> get() = _observeWords

    // connection
    private var _showNoConnection = MutableLiveData<Boolean>()
    val showNoConnection: LiveData<Boolean>
        get() = _showNoConnection


    fun initData() {
        if (isInternetAvailable) {
            showLoadingProcess.value = true
            viewModelScope.launch {
                fetchInstaBugWebSiteWords()
            }
        } else {
            viewModelScope.launch {
                getLocalData()
            }
        }
    }

    // get instabug web site word
    private suspend fun fetchInstaBugWebSiteWords() {
        val response = repository.fetchInstaBugWebSiteWords(BuildConfig.A_BASE_L)
        if (response.isNotEmpty()) {
            _observeWords.value =
                setWordsMap((Jsoup.parse(response) as Document).text())

        } else {
            showLoadingProcess.value = false
            viewModelScope.launch {
                getLocalData()
            }
        }
    }

    // split and remove special char from the response
    // then split in each space
    // then check if the word already in the map or not
    fun setWordsMap(siteWords: String): HashMap<String, Int> {
        val regex = Regex("[^A-Za-z0-9]")
        val splitSiteWords = siteWords.toLowerCase(Locale.ROOT).replace(",", "").split(" ")

        splitSiteWords.map {
            var word = it.trim()
            word = regex.replace(word, "")
            var lastCountValue = wordsMap[word]
            if (lastCountValue == null) {
                lastCountValue = 0
            }
            if (word.isNotEmpty()) wordsMap[word] = lastCountValue + 1
        }
        showLoadingProcess.value = false

        return wordsMap
    }

    //save all words with count in room db
    fun saveWordsInDb() {
        val storeWordList = arrayListOf<Word>()
        wordsMap.map {
            storeWordList.add(Word(it.key, it.value))
        }
        insertNewWord(*storeWordList.toTypedArray())

    }

    private fun insertNewWord(vararg words: Word) = viewModelScope.launch {
        wordRepository?.insert(* words)
    }

    // get all saved words in case of no internet
    private suspend fun getLocalData() {
        withContext(Dispatchers.IO) {
            wordRepository?.getAllWords()?.map { word ->
                wordsMap[word.word] = word.count
            }
        }
        _observeWords.value = wordsMap
        _showNoConnection.value = wordsMap.isNullOrEmpty()
    }
}