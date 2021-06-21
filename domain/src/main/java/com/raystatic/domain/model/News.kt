package com.raystatic.domain.model

data class News(
    val source:String?=null,
    val author:String?=null,
    val title:String,
    val description:String?=null,
    val url:String,
    val urlToImage:String?=null,
    val publishedAt:String,
    val content:String?=null,
    var isBookmarked:Boolean=false
)
