package com.codecamp.newsx.di

import android.content.Context
import androidx.room.Room
import com.codecamp.newsx.data.local.ArticleDatabase
import com.codecamp.newsx.prefs.ScrollStatePrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun installDatabase(
        @ApplicationContext context: Context
    ) : ArticleDatabase {
        return Room.databaseBuilder(
            context,
            ArticleDatabase::class.java,
            "article_Db"
        ).build()
    }

    @Provides
    @Singleton
    fun installSharedPref(@ApplicationContext context: Context) : ScrollStatePrefs {
        return ScrollStatePrefs(context)
    }
}