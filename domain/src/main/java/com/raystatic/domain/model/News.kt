package com.raystatic.domain.model

data class News(
    val source:String?="",
    val author:String?="",
    val title:String,
    val description:String?="",
    val url:String,
    val urlToImage:String?="",
    val publishedAt:String,
    val content:String
)
