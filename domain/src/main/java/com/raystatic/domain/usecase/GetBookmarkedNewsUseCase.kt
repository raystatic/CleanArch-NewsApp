package com.raystatic.domain.usecase

import com.raystatic.domain.Resource
import com.raystatic.domain.model.News
import com.raystatic.domain.repository.BookmarkedRepository
import com.raystatic.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkedNewsUseCase @Inject constructor(
    private val repository: BookmarkedRepository
) {

    suspend fun execute():Flow<List<News>>{
        return repository.getBookmarkedNews()
    }

}