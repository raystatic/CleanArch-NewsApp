package com.raystatic.data.pagingsource

import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.raystatic.data.datasource.LocalDataSource
import com.raystatic.data.datasource.RemoteDataSource
import com.raystatic.data.mapper.BookmarkedNewsMapper
import com.raystatic.data.mapper.NewsMapper
import com.raystatic.data.response.Article
import com.raystatic.domain.model.News
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toCollection
import java.lang.Exception

private const val NEWS_STARTING_PAGE_INDEX = 1
class TrendingNewsPagingSource(
    private val remoteDataSource: RemoteDataSource,
    private val country:String,
    private val mapper: NewsMapper,
    private val localDataSource: LocalDataSource,
    private val bookmarkedNewsMapper: BookmarkedNewsMapper
):PagingSource<Int, News>() {

    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {

        val position = params.key ?: NEWS_STARTING_PAGE_INDEX
        return try {
            val response = remoteDataSource.getTrendingNews(country, position)
            val nextKey = if (response.articles.isEmpty()){
                null
            }else{
                position + 1
            }

            val articles = response.articles

            Log.d("TAG", "loadDebug: before ${articles.map { it.isBookmarked }}")


            val bookmarks = localDataSource.getBookmarkedNews().first()

            articles.forEach {
                if (it.title in bookmarks.map { it.title }){
                    it.isBookmarked = true
                }
            }

            Log.d("BOOKMARKDEBUG", "load: ${bookmarks.map { it.title }}")

            Log.d("TAG", "loadDebug: ${articles.map { it.isBookmarked }}")

            LoadResult.Page(
                data = mapper.mapToNewsList(articles),
                prevKey = if (position == NEWS_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        }catch (e:Exception){
            return LoadResult.Error(e)
        }

    }

    companion object{
        const val NETWORK_PAGE_SIZE = 50
    }
}