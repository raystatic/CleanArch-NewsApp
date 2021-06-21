package com.raystatic.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.raystatic.data.datasource.RemoteDataSource
import com.raystatic.data.mapper.NewsMapper
import com.raystatic.data.response.Article
import com.raystatic.domain.model.News
import java.lang.Exception

private const val NEWS_STARTING_PAGE_INDEX = 1
class TrendingNewsPagingSource(
    private val remoteDataSource: RemoteDataSource,
    private val country:String,
    private val mapper: NewsMapper
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
            LoadResult.Page(
                data = mapper.mapToNewsList(response.articles),
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