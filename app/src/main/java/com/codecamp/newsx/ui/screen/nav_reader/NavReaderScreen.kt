package com.codecamp.newsx.ui.screen.nav_reader

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.codecamp.newsx.ui.screen.article.ArticleScreen

@Composable
fun NavReaderScreen(paddingValues: PaddingValues, onClicked: (Int) -> Unit) {
    ArticleScreen(paddingValues = paddingValues, onClicked = onClicked)
}