package com.raystatic.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raystatic.data.room.dao.BookmarkedDao
import com.raystatic.data.room.entity.BookmarkedNews

@Database(
    entities = [BookmarkedNews::class],
    version = 1
)
abstract class NewsAppDB: RoomDatabase() {

    abstract fun getBookmarkedDao():BookmarkedDao

}