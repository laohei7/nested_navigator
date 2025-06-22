package com.laohei.nested_navigator.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEach
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.laohei.nested_navigator.app.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainGraph(
    navigateToRoute: (Route) -> Unit
) {
    val mainNavController = rememberNavController()
    val bottomItems = remember {
        listOf(
            Triple(Icons.Default.Home, "Home", Route.MainGraph.Home),
            Triple(Icons.Default.AccountBox, "Subscription", Route.MainGraph.Subscription),
            Triple(Icons.Default.AccountCircle, "Profile", Route.MainGraph.Profile)
        )
    }

    val currentDestination by mainNavController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    IconButton(onClick = { navigateToRoute.invoke(Route.Settings()) }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = Icons.Default.Settings.name,
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                bottomItems.fastForEach {
                    // 注意使用 hasRoute(KClass)方法
                    val isCurrent =
                        currentDestination?.destination?.hasRoute(it.third::class) == true
                    NavigationBarItem(
                        selected = isCurrent,
                        onClick = {
                            if (isCurrent) {
                                return@NavigationBarItem
                            }
                            mainNavController.navigate(it.third) {
                                popUpTo(Route.MainGraph.Home) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = it.first,
                                contentDescription = it.first.name
                            )
                        },
                        label = {
                            Text(text = it.second)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            navController = mainNavController,
            startDestination = Route.MainGraph.Home
        ) {
            composable<Route.MainGraph.Home> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Home")
                }
            }
            composable<Route.MainGraph.Subscription> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Subscription")
                }
            }
            composable<Route.MainGraph.Profile> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Profile")
                }
            }
        }
    }
}