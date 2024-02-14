package com.codecamp.newsx.ui.screen.article

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.codecamp.newsx.ui.viewmodel.ArticleViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

@OptIn(FlowPreview::class)
@Composable
fun ArticleScreen(paddingValues: PaddingValues,viewModel: ArticleViewModel = hiltViewModel(), onClicked: (Int) -> Unit) {

    //fetch & restore scroll position from shared prefs
    val scrollIndex = remember { viewModel.getScrollIndex }
    val lazyArticleItems = viewModel.articlesFlows.collectAsLazyPagingItems()
    val isLoadingRefreshed = viewModel.isLoadingRefreshed
    val isScrollClicked = remember { mutableStateOf(false) }


    val lazyListState = rememberLazyListState(
        initialFirstVisibleItemIndex = scrollIndex
    )

    LaunchedEffect(key1 = isScrollClicked.value, block = {
        if(isScrollClicked.value) {
            lazyListState.animateScrollToItem(0)
            isScrollClicked.value = false
            isLoadingRefreshed.value = false
        }
    })


    LaunchedEffect(key1 = lazyListState, block = {
        snapshotFlow {
            lazyListState.firstVisibleItemIndex
        }.debounce(1000L)
            .collectLatest { index ->
                viewModel.setScrollIndex(index)
            }
    })

    Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
        if (lazyArticleItems.itemCount < 1 && lazyArticleItems.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        } else {
            ArticleList(lazyListState, lazyArticleItems, onClicked)
        }
        LoadRefreshedData(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .wrapContentWidth()
                .padding(top = 20.dp), isVisible = isLoadingRefreshed.value && lazyListState.canScrollBackward
        ) {
            isScrollClicked.value = true
        }
    }
}