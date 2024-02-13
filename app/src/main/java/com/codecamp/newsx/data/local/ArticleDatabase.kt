package com.codecamp.newsx.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArticleEntity::class, ArticleRemoteKey::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {
    abstract val articleDao:ArticleDao
    abstract val remoteDao:ArticleRemoteDao
}