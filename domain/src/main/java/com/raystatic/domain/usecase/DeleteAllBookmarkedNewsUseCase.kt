package com.raystatic.domain.usecase

import com.raystatic.domain.model.News
import com.raystatic.domain.repository.BookmarkedRepository
import javax.inject.Inject

class DeleteAllBookmarkedNewsUseCase @Inject constructor(
    private val repository: BookmarkedRepository
) {

    suspend fun execute(){
        repository.deleteAllBookmarks()
    }

}