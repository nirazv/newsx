package com.codecamp.newsx.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_entity")
data class ArticleEntity(
    @PrimaryKey
    val id: Int,
    val date: String,
    val slug: String,
    val title: String,
    val content: String,
    val image: String
)