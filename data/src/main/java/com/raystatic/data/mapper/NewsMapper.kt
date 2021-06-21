package com.raystatic.data.mapper

import com.raystatic.data.response.Article
import com.raystatic.domain.model.News
import javax.inject.Inject

class NewsMapper @Inject constructor() {

    fun mapToNewsList(responseList: List<Article>):List<News>{
        return responseList.map { mapToNews(it) }
    }

    fun mapToNews(article: Article):News{
        return News(
            source = article.source?.name,
            author = article.author,
            title = article.title,
            description = article.description,
            url = article.url,
            urlToImage = article.urlToImage,
            publishedAt = article.publishedAt,
            content = article.content,
            isBookmarked = article.isBookmarked
        )
    }

}