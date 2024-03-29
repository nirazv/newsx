package com.codecamp.newsx.ui.screen.article

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.codecamp.newsx.data.local.ArticleEntity

@Composable
fun ArticleList(lazyListState: LazyListState, lazyArticleItems: LazyPagingItems<ArticleEntity>) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState
        ) {
            lazyArticleItems?.let {
                items(
                    count = lazyArticleItems.itemCount,
                    key = lazyArticleItems.itemKey { it.id }
                ) {
                    if(lazyArticleItems[it] != null ) {
                        ArticleItem(onClicked = {}, article = lazyArticleItems[it]!!)
                    }
                }
            }
        }
}