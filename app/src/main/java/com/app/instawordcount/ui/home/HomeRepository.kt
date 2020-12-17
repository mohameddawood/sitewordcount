package com.app.instawordcount.ui.home

import com.app.instawordcount.manager.utilities.convertInputStreamToString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class HomeRepository : KoinComponent {

    suspend fun fetchInstaBugWebSiteWords(siteUrl: String): String {
        return withContext(Dispatchers.IO) {
            val inputStream: InputStream
            val url = URL(siteUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.connect()
            inputStream = connection.inputStream
            inputStream?.convertInputStreamToString() ?: ""
        }
    }
}

