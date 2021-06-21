package com.raystatic.data.datasource

import com.raystatic.data.room.NewsAppDB
import com.raystatic.data.room.dao.BookmarkedDao
import com.raystatic.data.room.entity.BookmarkedNews
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: BookmarkedDao
) {

    suspend fun insertBookmarkedDao(bookmarkedNews: BookmarkedNews) = dao.insertBookmarkedNews(bookmarkedNews)

    fun getBookmarkedNews() = dao.getBookmarkedNews()

    suspend fun deleteBookmarkedByTitle(title:String) = dao.deleteBookmarkedNewsByTitle(title)

    suspend fun deleteAllBookmarked() = dao.deleteAllBookmarkedNews()

}