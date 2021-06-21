package com.raystatic.data.mapper

import com.raystatic.data.room.entity.BookmarkedNews
import com.raystatic.domain.model.News
import javax.inject.Inject

class BookmarkedNewsMapper @Inject constructor() {

    fun mapToNews(bookmarkedNews: BookmarkedNews):News{
        return News(
            source = bookmarkedNews.source,
            title = bookmarkedNews.title,
            author = bookmarkedNews.author,
            publishedAt = bookmarkedNews.publishedAt,
            content = bookmarkedNews.content,
            urlToImage = bookmarkedNews.imageUrl,
            url = bookmarkedNews.url
        )
    }

    fun mapToNewsList(list:List<BookmarkedNews>):List<News>{
        return list.map { mapToNews(it) }
    }

    fun mapToBookmarkedNews(news:News):BookmarkedNews{
        return BookmarkedNews(
            source = news.source,
            title = news.title,
            author = news.author,
            publishedAt = news.publishedAt,
            content = news.content,
            imageUrl = news.urlToImage,
            url = news.url
        )
    }

    fun mapToBookmarkedNewsList(list:List<News>):List<BookmarkedNews>{
        return list.map { mapToBookmarkedNews(it) }
    }

}