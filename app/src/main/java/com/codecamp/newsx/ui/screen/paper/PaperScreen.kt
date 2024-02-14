package com.codecamp.newsx.ui.screen.paper

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import coil.compose.AsyncImage
import com.codecamp.newsx.R
import com.codecamp.newsx.data.local.ArticleEntity
import com.codecamp.newsx.data.utils.TaskLoadState
import com.codecamp.newsx.data.utils.getTimeAgo
import com.codecamp.newsx.ui.viewmodel.ArticleViewModel
import de.charlex.compose.material3.HtmlText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaperScreen(navBackStackEntry: NavBackStackEntry, onClick: () -> Unit) {

    val id = navBackStackEntry.arguments!!.getInt("id")

    val viewModel: ArticleViewModel = hiltViewModel()
    val selectedTask = viewModel.selectedTask.collectAsState()

    val configuration = LocalConfiguration.current

    val context = LocalContext.current

    LaunchedEffect(key1 = id, block = {
        viewModel.getArticleById(id)
    })

    if (selectedTask.value is TaskLoadState.Success) {
        val article = (selectedTask.value as TaskLoadState.Success<ArticleEntity>).data
        BottomSheetScaffold(
            sheetSwipeEnabled = false,
            topBar = { Spacer(modifier = Modifier.size(64.dp)) },
            scaffoldState = rememberBottomSheetScaffoldState(),
            sheetDragHandle = {
                Icon(
                    modifier = Modifier.width(100.dp),
                    painter = painterResource(id = R.drawable.ic_line),
                    tint = MaterialTheme.colorScheme.primaryContainer,
                    contentDescription = "")
            },
            sheetPeekHeight = configuration.screenHeightDp.minus(340 - 16).dp,
            sheetShape = RectangleShape,
            sheetContent = {
                PaperScreenSheetContent(article)
            }
        ) {
            AsyncImage(
                model = article.image,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(260.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun PaperScreenSheetContent(article: ArticleEntity) {
    Column(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        HtmlText(
            text = article.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 10.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_dot), contentDescription = "",
                tint = MaterialTheme.colorScheme.primaryContainer
            )
            Spacer(modifier = Modifier.size(6.dp))
            Text(text = getTimeAgo(article.date), maxLines = 1)
        }
        Spacer(modifier = Modifier.size(5.dp))
        Divider()
        Spacer(modifier = Modifier.size(20.dp))
        HtmlText(text = article.content)
    }
}


