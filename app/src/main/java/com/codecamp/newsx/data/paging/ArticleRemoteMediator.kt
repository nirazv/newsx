package com.codecamp.newsx.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.codecamp.newsx.data.local.ArticleDatabase
import com.codecamp.newsx.data.local.ArticleEntity
import com.codecamp.newsx.data.local.ArticleRemoteKey
import com.codecamp.newsx.data.remote.ArticleApi
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ArticleRemoteMediator(
    private val articleDatabase: ArticleDatabase,
    private val articleApi: ArticleApi
) : RemoteMediator<Int, ArticleEntity>() {

    private val articleRemoteKey = articleDatabase.remoteDao
    private val articleDao = articleDatabase.articleDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleEntity>
    ): MediatorResult {
        return try {
            val loadPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    remoteKeys?.prevPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    remoteKeys?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
            }

            //get response from network
            val response = articleApi.getArticles(
                page = loadPage,
                pageSize = 20
            )

            val endOfPaginationResponse = response.isEmpty()
            val prevPage = if (loadPage == 1) null else loadPage - 1
            val nextPage = if (endOfPaginationResponse) null else loadPage + 1

            articleDatabase.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    articleDao.removeArticles()
                    articleRemoteKey.removeRemoteKeys()
                }

                val keys = response.map {newspaper ->
                    ArticleRemoteKey(
                        id = newspaper.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }

                articleRemoteKey.setRemoteKeys(keys)
                articleDao.setArticles(response)

            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationResponse)

        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, ArticleEntity>
    ): ArticleRemoteKey? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { article ->
            articleRemoteKey.getRemoteKey(article.id)
        }
    } // end of getRemoteKeyForLastItem


    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, ArticleEntity>
    ): ArticleRemoteKey? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { article ->
            articleRemoteKey.getRemoteKey(article.id)
        }
    } //end of getRemoteKeyForFirstItem

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, ArticleEntity>
    ): ArticleRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                articleRemoteKey.getRemoteKey(id)
            }
        }
    } //end of getRemoteKeyClosestToCurrentPosition

}