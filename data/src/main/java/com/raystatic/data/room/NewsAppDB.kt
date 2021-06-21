package com.raystatic.data.room

import androidx.room.RoomDatabase
import com.raystatic.data.room.dao.BookmarkedDao

abstract class NewsAppDB: RoomDatabase() {

    abstract fun getBookmarkedDao():BookmarkedDao

}