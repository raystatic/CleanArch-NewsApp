package com.raystatic.domain.repository

import com.raystatic.domain.Resource
import com.raystatic.domain.model.News
import kotlinx.coroutines.flow.Flow

interface BookmarkedRepository {

    suspend fun getBookmarkedNews(): Flow<Resource<List<News>>>

}