package com.raystatic.domain.repository

import androidx.paging.PagingData
import com.raystatic.domain.Resource
import com.raystatic.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsList(country:String):Flow<PagingData<News>>

}