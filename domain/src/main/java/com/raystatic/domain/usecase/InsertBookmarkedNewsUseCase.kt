package com.raystatic.domain.usecase

import com.raystatic.domain.model.News
import com.raystatic.domain.repository.BookmarkedRepository
import javax.inject.Inject

class InsertBookmarkedNewsUseCase @Inject constructor(
    private val repository: BookmarkedRepository
) {

    suspend fun execute(news:News){
        repository.insertBookmarkedNews(news)
    }

}