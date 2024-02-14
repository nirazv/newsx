package com.codecamp.newsx.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.codecamp.newsx.data.local.ArticleEntity
import com.codecamp.newsx.data.utils.TaskLoadState
import com.codecamp.newsx.domain.usecase.ArticlePagedUseCase
import com.codecamp.newsx.prefs.ScrollStatePrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val articlePagedUseCase: ArticlePagedUseCase,
    private val scrollStatePrefs: ScrollStatePrefs
) : ViewModel() {

    val isLoadingRefreshed = mutableStateOf(false)

    val articlesFlows = articlePagedUseCase().cachedIn(viewModelScope)
    val getScrollIndex = scrollStatePrefs.getIndexValueFromPrefs()

    val selectedTask: MutableStateFlow<TaskLoadState<ArticleEntity>> = MutableStateFlow(TaskLoadState.Loading)

    private val itemStackId = mutableIntStateOf(0)

    fun setScrollIndex(index: Int) {
        scrollStatePrefs.saveIndexValueToPrefs(index)
    }


    init {
        renderIsLoadingRefreshed()
        observeTimestamp()
    }

    private fun renderIsLoadingRefreshed() {
        if(getScrollIndex > 5) isLoadingRefreshed.value = true
    }

    //observe latest data and show isLoadingButton
    private fun observeTimestamp() {
        viewModelScope.launch {
            articlePagedUseCase.getTimestamp().collect {
                if(itemStackId.intValue == 0) {
                    itemStackId.intValue = it
                } else if (itemStackId.intValue < it) {
                    isLoadingRefreshed.value = true
                }
            }
        }
    }

    fun getArticleById(id: Int) {
        viewModelScope.launch {
            articlePagedUseCase.getArticleById(id).collect {
                selectedTask.value = TaskLoadState.Success(it)
            }
        }
    }


}