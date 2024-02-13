package com.codecamp.newsx.data.remote

import com.codecamp.newsx.data.local.ArticleEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {

    @GET("posts")
    suspend fun getArticles(
        @Query("page") page:Int,
        @Query("per_page") pageSize:Int
    ) : List<ArticleEntity>

    companion object {
        const val URL_ENDPOINTS = "https://ap-south-1.aws.data.mongodb-api.com/app/application-0-rmroy/endpoint/"
    }
}