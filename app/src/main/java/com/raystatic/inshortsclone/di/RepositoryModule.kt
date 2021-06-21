package com.raystatic.inshortsclone.di

import com.raystatic.data.datasource.LocalDataSource
import com.raystatic.data.datasource.RemoteDataSource
import com.raystatic.data.mapper.BookmarkedNewsMapper
import com.raystatic.data.mapper.NewsMapper
import com.raystatic.data.repository.BookmarkedNewsRepositoryImpl
import com.raystatic.data.repository.NewsRepositoryImpl
import com.raystatic.domain.repository.BookmarkedRepository
import com.raystatic.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(remoteDataSource: RemoteDataSource, mapper: NewsMapper): NewsRepository {
        return NewsRepositoryImpl(remoteDataSource, mapper)
    }

    @Provides
    @Singleton
    fun provideBookmarkedNewsRepository(localDataSource: LocalDataSource,mapper: BookmarkedNewsMapper): BookmarkedRepository {
        return BookmarkedNewsRepositoryImpl(localDataSource, mapper)
    }

}