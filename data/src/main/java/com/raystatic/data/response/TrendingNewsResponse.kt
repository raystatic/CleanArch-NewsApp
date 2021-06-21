package com.raystatic.data.response

data class TrendingNewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)