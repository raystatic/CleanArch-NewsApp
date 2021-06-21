package com.raystatic.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raystatic.data.room.entity.BookmarkedNews
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmarkedNews(bookmarkedNews: BookmarkedNews)

    @Query("SELECT * FROM bookmarks ORDER BY publishedAt DESC")
    fun getBookmarkedNews():Flow<List<BookmarkedNews>>

    @Query("DELETE FROM bookmarks WHERE title=:title")
    suspend fun deleteBookmarkedNewsByTitle(title:String)

    @Query("DELETE FROM bookmarks")
    suspend fun deleteAllBookmarkedNews()
}