package com.raystatic.data.repository

import com.raystatic.data.datasource.RemoteDataSource
import com.raystatic.data.mapper.NewsMapper
import com.raystatic.domain.Resource
import com.raystatic.domain.model.News
import com.raystatic.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class NewsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val newsMapper: NewsMapper
):NewsRepository{

    override suspend fun getNewsList(country: String): Flow<Resource<List<News>>> {
        return flow {
            emit(Resource.Loading)
            try {
                val response =remoteDataSource.getTrendingNews(country)
                val result = newsMapper.mapToNewsList(response.articles)
                println("RAYDEBUG: this is called. $result")
                emit(Resource.Success(result))
            }catch (e:Exception){
                e.printStackTrace()
                Resource.Error(e)
            }
        }
    }
}