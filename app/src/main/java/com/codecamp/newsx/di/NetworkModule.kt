package com.codecamp.newsx.di

//import com.codecamp.newsx.data.fcm.FcmApi
import com.codecamp.newsx.data.remote.ArticleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    val intercepter = HttpLoggingInterceptor().apply {
//        this.level = HttpLoggingInterceptor.Level.BASIC
//    }

    val client = OkHttpClient.Builder()
//        .addInterceptor(intercepter)
        .build()

    @Provides
    @Singleton
    fun installRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(ArticleApi.URL_ENDPOINTS)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun installArticleApi(retrofit: Retrofit) : ArticleApi {
        return retrofit.create(ArticleApi::class.java)
    }
//    @Provides
//    @Singleton
//    fun installFcmApi(retrofit: Retrofit) : FcmApi {
//        return retrofit.create(FcmApi::class.java)
//    }
}