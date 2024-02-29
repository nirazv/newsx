package com.codecamp.newsx.ui.screen.main

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.codecamp.newsx.ui.navigation.BottomNavStack
import com.codecamp.newsx.ui.navigation.navStackItems
import com.codecamp.newsx.ui.theme.iceBergBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination: NavDestination? = navBackStackEntry?.destination

    currentDestination?.let {
        when(it.route) {
            BottomNavStack.Readers.route, BottomNavStack.Recommends.route, BottomNavStack.Settings.route ->
            NavigationBar(
                containerColor = iceBergBlue
            ) {
                navStackItems.forEach { item: BottomNavStack ->
                    val isSelected =
                        currentDestination.hierarchy.any { it.route == item.route }
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            BadgedBox(badge = {
                                if (item.route == BottomNavStack.Recommends.route) Badge(
                                    modifier = Modifier.size(
                                        8.dp
                                    )
                                )
                            }) {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = ""
                                )
                            }
                        },
                        label = {
                            Text(text = item.label)
                        }
                    )
                }
            }
        }
    }
}