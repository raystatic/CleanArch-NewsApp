package com.raystatic.domain.usecase

import androidx.paging.PagingData
import com.raystatic.domain.model.News
import com.raystatic.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsListUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    suspend fun execute(country:String):Flow<PagingData<News>>{
        return repository.getNewsList(country)
    }

}