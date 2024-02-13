package com.codecamp.newsx.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ArticleRemoteDao {

    @Query("SELECT * FROM article_remote_key WHERE id=:key")
    suspend fun getRemoteKey(key: Int) : ArticleRemoteKey

    @Query("DELETE FROM article_remote_key")
    suspend fun removeRemoteKeys()

    @Upsert
    suspend fun setRemoteKeys(keys:List<ArticleRemoteKey>)

}