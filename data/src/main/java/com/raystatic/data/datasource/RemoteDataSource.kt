package com.raystatic.data.datasource

import com.raystatic.data.NewsApi
import com.raystatic.data.response.TrendingNewsResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val newsApi: NewsApi
) {

    suspend fun getTrendingNews(country:String, page:Int):TrendingNewsResponse{
        return newsApi.getTrendingNews(country = country,apiKey = NewsApi.API_KEY, page = page)
    }

}