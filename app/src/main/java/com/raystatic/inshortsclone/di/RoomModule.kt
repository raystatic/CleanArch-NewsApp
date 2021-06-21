package com.raystatic.inshortsclone.di

import android.content.Context
import androidx.room.Room
import com.raystatic.data.room.NewsAppDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRunningDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        NewsAppDB::class.java,
        "NewAppDb.db"
    ).fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideBookmarkedNewsDao(db: NewsAppDB) = db.getBookmarkedDao()

}