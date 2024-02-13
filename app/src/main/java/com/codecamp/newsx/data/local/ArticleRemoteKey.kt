package com.codecamp.newsx.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_remote_key")
data class ArticleRemoteKey(
    @PrimaryKey
    val id:Int,
    val prevPage: Int?,
    val nextPage: Int?
)