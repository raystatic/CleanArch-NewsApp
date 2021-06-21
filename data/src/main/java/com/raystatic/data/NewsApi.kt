package com.raystatic.data

import com.raystatic.data.response.TrendingNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTrendingNews(
        @Query("country") country:String,
        @Query("apiKey") apiKey:String
    ):TrendingNewsResponse

    companion object{
        const val API_KEY = "1199abc09655435b829cac50f16f9265"
        const val BASE_URL = "https://newsapi.org/v2/"
    }

}