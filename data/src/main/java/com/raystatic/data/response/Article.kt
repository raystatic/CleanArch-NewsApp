package com.raystatic.data.response

data class Article(
    val author: String?=null,
    val content: String?=null,
    val description: String?=null,
    val publishedAt: String,
    val source: Source?=null,
    val title: String,
    val url: String,
    val urlToImage: String?=null,
    var isBookmarked:Boolean=false
)