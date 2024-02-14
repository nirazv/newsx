package com.codecamp.newsx.ui.screen.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.net.wifi.hotspot2.pps.HomeSp
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.codecamp.newsx.R
import com.codecamp.newsx.data.local.ArticleEntity
import com.codecamp.newsx.data.utils.TaskLoadState
import com.codecamp.newsx.ui.navigation.BottomNavStack
import com.codecamp.newsx.ui.screen.paper.PaperScreenTopBar
import com.codecamp.newsx.ui.viewmodel.ArticleViewModel

@Composable
fun ConfigureTopBar(navController: NavController,viewModel:ArticleViewModel = hiltViewModel()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination: NavDestination? = navBackStackEntry?.destination
    val context = LocalContext.current
    val selectedTask = viewModel.selectedTask.collectAsState()
    currentDestination?.let {
        when (it.route) {
            BottomNavStack.Readers.route, BottomNavStack.Recommends.route, BottomNavStack.Settings.route -> HomeTopBar()
            else -> {
                PaperScreenTopBar(onClick = {
                    navController.popBackStack()
                }) {
                    if(selectedTask.value is TaskLoadState.Success) {
                        val task = (selectedTask.value as TaskLoadState.Success).data
                        shareClick(context,task.title,task.slug)
                    }
                }
            }
        }
    }
}


fun shareClick(context: Context, title:String, url:String) {
    val share = Intent.createChooser(Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "https://developer.android.com/training/sharing/")

        // (Optional) Here you're setting the title of the content
        putExtra(Intent.EXTRA_TITLE, title)

        // (Optional) Here you're passing a content URI to an image to be displayed
        data = Uri.parse("https://agresarbharat.com/$url")
        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
    }, null)
    context.startActivity(share)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {
    TopAppBar(
        modifier = Modifier.border(0.5.dp, Color.Gray),
        title = {
            Text(text = "AgresarBharat")
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            Icon(
                modifier = Modifier.padding(horizontal = 12.dp),
                painter = painterResource(id = R.drawable.ic_nav_newsstand),
                contentDescription = ""
            )
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "")
            }
        }
    )
}