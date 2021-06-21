package com.raystatic.domain.repository

import com.raystatic.domain.Resource
import com.raystatic.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsListRepository {

    suspend fun getNewsList(country:String):Flow<Resource<List<News>>>

}