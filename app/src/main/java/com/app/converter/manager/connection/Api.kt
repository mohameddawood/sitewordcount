package com.app.converter.manager.connection

import com.app.converter.manager.connection.ApiEndPoints.CONVERT
import com.app.converter.manager.connection.ApiEndPoints.RATES
import com.app.converter.model.entities.ConvertRateResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET(RATES)
    fun getRates(): Flowable<Resource<HashMap<String, Double>>>

    @GET(CONVERT)
    fun convertRate(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Double
    ): Flowable<Resource<ConvertRateResponse>>
}