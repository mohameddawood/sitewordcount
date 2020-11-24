package com.app.converter.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.app.converter.BuildConfig
import com.app.converter.manager.connection.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {

    single {
        AppSchedulerProvider()
    }

    factory {
        CompositeDisposable()
    }


    // Prefs manager injection
    single {
        PreferencesManagerRepository(get(), get())
    }
    single {
        Gson()
    }



    single {
        ApiHeader(
            deviceToken = "",
            deviceId = "",
            countryId = 0,
            languageId = "1",
            language = "en"
        )
    }

    single { provideSettingsPreferences(androidApplication()) }


    single {
        val client = OkHttpClient.Builder()
        //if (BuildConfig.BUILD_TYPE == "debug") {
            client.addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            client.addInterceptor(ChuckInterceptor(get()))
        //}


        client.addInterceptor(Interceptor { chain ->
            val original = chain.request()
            val apiHeader = (get() as ApiHeader)
            val request =
                original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method, original.body).build()

            return@Interceptor chain.proceed(request)
        })

        client.connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).build()
        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.A_BASE_L).client(client.build())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setExclusionStrategies(SerializedNameOnlyStrategy()).create()
                )
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()

        retrofit.create(Api::class.java)
    }
}
private const val PREFERENCES_FILE_KEY = "com.example.settings_preferences"

fun provideSettingsPreferences(app: Application): SharedPreferences =
    app.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
