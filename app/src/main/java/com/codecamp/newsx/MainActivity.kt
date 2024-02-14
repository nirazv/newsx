package com.codecamp.newsx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.codecamp.newsx.ui.navigation.AppNavigation

import com.codecamp.newsx.ui.screen.article.ArticleScreen
import com.codecamp.newsx.ui.screen.main.ConfigureTopBar
import com.codecamp.newsx.ui.screen.main.HomeBottomBar
import com.codecamp.newsx.ui.screen.main.HomeTopBar
import com.codecamp.newsx.ui.theme.NewsXTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            installSplashScreen()
            NewsXTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = { ConfigureTopBar(navController) },
                        content = { paddingValues ->
                            AppNavigation(
                                navController = navController,
                                paddingValues = paddingValues
                            )
                        },
                        bottomBar = { HomeBottomBar(navController = navController) }
                    )
                }
            }
        }
    }
}

