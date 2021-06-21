package com.raystatic.domain.repository

import com.raystatic.domain.model.News
import kotlinx.coroutines.flow.Flow

interface BookmarkedRepository {

    suspend fun getBookmarkedNews(): Flow<List<News>>

    suspend fun insertBookmarkedNews(news: News)

    suspend fun deleteBookmarkedNewsByTitle(title:String)

    suspend fun deleteAllBookmarks()

}