package com.raystatic.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.raystatic.data.datasource.RemoteDataSource
import com.raystatic.data.mapper.NewsMapper
import com.raystatic.data.pagingsource.TrendingNewsPagingSource
import com.raystatic.domain.model.News
import com.raystatic.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val newsMapper: NewsMapper
):NewsRepository{

    override suspend fun getNewsList(country: String): Flow<PagingData<News>> {

        return Pager(
            config = PagingConfig(
                pageSize = TrendingNewsPagingSource.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                TrendingNewsPagingSource(remoteDataSource, country,newsMapper)
            }
        ).flow
    }
}