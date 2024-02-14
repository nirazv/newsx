package com.codecamp.newsx.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.codecamp.newsx.data.local.ArticleDatabase
import com.codecamp.newsx.data.local.ArticleEntity
import com.codecamp.newsx.data.paging.ArticleRemoteMediator
import com.codecamp.newsx.data.remote.ArticleApi
import com.codecamp.newsx.data.utils.toObjectTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticlePagedUseCase @Inject constructor(
    private val articleDatabase: ArticleDatabase,
    private val articleApi: ArticleApi
){

    @OptIn(ExperimentalPagingApi::class)
    operator fun invoke(): Flow<PagingData<ArticleEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30
            ),
            remoteMediator = ArticleRemoteMediator(
                articleDatabase,
                articleApi
            ),
            pagingSourceFactory = {articleDatabase.articleDao.getArticles()}
        ).flow
            .map {
            it.map {entity ->
                entity.toObjectTime()
            }
        }
    }

    fun getTimestamp() : Flow<Int> {
        return articleDatabase.articleDao.getObjectTimestamp()
    }

    fun getArticleById(id:Int) : Flow<ArticleEntity> {
        return articleDatabase.articleDao.getArticleById(id)
    }
}