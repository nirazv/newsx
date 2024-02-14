package com.codecamp.newsx.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.codecamp.newsx.ui.screen.nav_reader.NavReaderScreen
import com.codecamp.newsx.ui.screen.nav_recommend.NavRecommendsScreen
import com.codecamp.newsx.ui.screen.nav_setting.NavSettingScreen
import com.codecamp.newsx.ui.screen.paper.PaperScreen

@Composable
fun AppNavigation(navController: NavHostController, paddingValues: PaddingValues) {

    NavHost(navController = navController, startDestination = BottomNavStack.Readers.route) {

        composable(
            route = BottomNavStack.Readers.route
        ) {
            NavReaderScreen(paddingValues = paddingValues) {
                navController.navigate("paper/${it}")
            }
        }
        composable(route = BottomNavStack.Recommends.route) {
            NavRecommendsScreen(paddingValues = paddingValues)
        }
        composable(route = BottomNavStack.Settings.route) {
            NavSettingScreen(paddingValues = paddingValues)
        }
        composable(
            route = "paper/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            PaperScreen(it) {
                navController.popBackStack()
            }
        }
    }
}