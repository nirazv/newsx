package com.codecamp.newsx.di

import com.codecamp.newsx.data.remote.ArticleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    val intercepter = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BASIC
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(intercepter)
        .build()

    @Provides
    @Singleton
    fun installArticleApi() : ArticleApi {
        return Retrofit.Builder()
            .baseUrl(ArticleApi.URL_ENDPOINTS)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ArticleApi::class.java)
    }
}