package com.raystatic.domain.usecase

import com.raystatic.domain.Resource
import com.raystatic.domain.model.News
import com.raystatic.domain.repository.NewsListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsListUseCase @Inject constructor(
    private val repository: NewsListRepository
) {

    suspend fun execute(country:String):Flow<Resource<List<News>>>{
        return repository.getNewsList(country)
    }

}