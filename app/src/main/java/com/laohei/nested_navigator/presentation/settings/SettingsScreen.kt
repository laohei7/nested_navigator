package com.laohei.nested_navigator.presentation.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.laohei.nested_navigator.app.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navigateToUp: () -> Unit
) {
    val settingsNavController = rememberNavController()
    val mainSettingList = remember {
        listOf(
            Pair("Video Settings", Route.Settings.VideoSetting),
            Pair("Other Settings", Route.Settings.OtherSetting),
        )
    }
    val currentDestination by settingsNavController.currentBackStackEntryAsState()
    val title by remember {
        derivedStateOf {
            mainSettingList.find {
                currentDestination?.destination?.hasRoute(it.second::class) == true
            }?.first ?: "Settings"
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        val isMainSetting =
                            currentDestination?.destination?.hasRoute<Route.Settings.MainSetting>() == true
                        if (isMainSetting) {
                            navigateToUp()
                        } else {
                            settingsNavController.navigateUp()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = Icons.AutoMirrored.Filled.ArrowBack.name
                        )
                    }
                },
                title = { Text(text = title) }
            )
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            navController = settingsNavController,
            startDestination = Route.Settings.MainSetting
        ) {
            composable<Route.Settings.MainSetting> {
                MainSettingsScreen(
                    settings = mainSettingList
                ) {
                    settingsNavController.navigate(it)
                }
            }
            composable<Route.Settings.VideoSetting> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Video Settings")
                }
            }
            composable<Route.Settings.OtherSetting> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Other Settings")
                }
            }
        }
    }
}