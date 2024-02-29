package com.codecamp.newsx.domain.model

data class Article(
    val id: Int,
    val date: String,
    val slug: String,
    val title: String,
    val image: String
)