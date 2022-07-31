package com.senthil.prabhu.mobile.app.android.technologyassessment.api

import com.senthil.prabhu.mobile.app.android.technologyassessment.models.Articles
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("/svc/mostpopular/v2/mostviewed/all-sections/{days}.json")
    suspend fun getArticles(
        @Path("days") days: String,
        @Query("api-key") apiKey: String,
    ) : Articles


    companion object Factory {
        fun create(): Api {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.nytimes.com")
                .build()
            return retrofit.create(Api::class.java);
        }
    }
}