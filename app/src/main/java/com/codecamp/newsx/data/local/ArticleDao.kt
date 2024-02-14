package com.codecamp.newsx.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM ARTICLE_ENTITY ORDER BY date DESC")
    fun getArticles() : PagingSource<Int, ArticleEntity>

    @Upsert
    suspend fun setArticles(articles:List<ArticleEntity>)

    @Query("DELETE FROM ARTICLE_ENTITY")
    suspend fun removeArticles()

    @Query("SELECT ID FROM ARTICLE_ENTITY ORDER BY DATE DESC LIMIT 1")
    fun getObjectTimestamp() : Flow<Int>

    @Query("SELECT * FROM ARTICLE_ENTITY WHERE ID=:id")
    fun getArticleById(id: Int): Flow<ArticleEntity>
}