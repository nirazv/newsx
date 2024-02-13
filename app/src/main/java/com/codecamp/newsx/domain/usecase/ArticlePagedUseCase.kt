package com.codecamp.newsx.domain.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.codecamp.newsx.data.local.ArticleDatabase
import com.codecamp.newsx.data.local.ArticleEntity
import com.codecamp.newsx.data.paging.ArticleRemoteMediator
import com.codecamp.newsx.data.remote.ArticleApi
import kotlinx.coroutines.flow.Flow
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
    }

    fun getTimestamp() : Flow<Int> {
        return articleDatabase.articleDao.getObjectTimestamp()
    }
}