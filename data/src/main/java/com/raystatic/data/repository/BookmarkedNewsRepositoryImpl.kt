package com.raystatic.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.raystatic.data.datasource.LocalDataSource
import com.raystatic.data.datasource.RemoteDataSource
import com.raystatic.data.mapper.BookmarkedNewsMapper
import com.raystatic.data.mapper.NewsMapper
import com.raystatic.data.pagingsource.TrendingNewsPagingSource
import com.raystatic.domain.Resource
import com.raystatic.domain.model.News
import com.raystatic.domain.repository.BookmarkedRepository
import com.raystatic.domain.repository.NewsRepository
import kotlinx.coroutines.flow.*
import java.lang.Exception

class BookmarkedNewsRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val bookmarkedNewsMapper: BookmarkedNewsMapper
):BookmarkedRepository{

    override suspend fun getBookmarkedNews(): Flow<List<News>> {
        return localDataSource.getBookmarkedNews().map { bookmarkedNewsMapper.mapToNewsList(it) }
    }

    override suspend fun insertBookmarkedNews(news: News) {
        val bookmarkedNews = bookmarkedNewsMapper.mapToBookmarkedNews(news)
        localDataSource.insertBookmarkedDao(bookmarkedNews)
    }

    override suspend fun deleteBookmarkedNewsByTitle(title: String) {
        localDataSource.deleteBookmarkedByTitle(title)
    }

    override suspend fun deleteAllBookmarks() {
        localDataSource.deleteAllBookmarked()
    }
}