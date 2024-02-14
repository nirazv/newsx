package com.codecamp.newsx.ui.navigation

import com.codecamp.newsx.R

sealed class BottomNavStack(
    val route: String,
    val label: String,
    val icon: Int
) {
    data object Recommends : BottomNavStack(
        route = "recommends",
        label = "Recommends",
        icon = R.drawable.ic_nav_newsstand
    )

    data object Readers : BottomNavStack(
        route = "readers",
        label = "Reader",
        icon = R.drawable.ic_nav_face
    )
    data object Settings : BottomNavStack(
        route = "settings",
        label = "Settings",
        icon = R.drawable.ic_nav_setting,
    )
}

val navStackItems = listOf(
    BottomNavStack.Recommends,
    BottomNavStack.Readers,
    BottomNavStack.Settings
)