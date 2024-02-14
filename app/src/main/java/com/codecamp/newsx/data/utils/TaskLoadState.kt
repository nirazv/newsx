package com.codecamp.newsx.data.utils

sealed class TaskLoadState<out T> {
    data object Loading: TaskLoadState<Nothing>()
    data class Success<T>(val data: T) : TaskLoadState<T>()
}