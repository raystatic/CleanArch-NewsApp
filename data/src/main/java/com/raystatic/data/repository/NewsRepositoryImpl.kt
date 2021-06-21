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
import com.raystatic.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val newsMapper: NewsMapper,
    private val bookmarkedNewsMapper: BookmarkedNewsMapper
):NewsRepository{

    override suspend fun getNewsList(country: String): Flow<PagingData<News>> {

        return Pager(
            config = PagingConfig(
                pageSize = TrendingNewsPagingSource.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                TrendingNewsPagingSource(remoteDataSource, country,newsMapper,localDataSource, bookmarkedNewsMapper)
            }
        ).flow
    }
}