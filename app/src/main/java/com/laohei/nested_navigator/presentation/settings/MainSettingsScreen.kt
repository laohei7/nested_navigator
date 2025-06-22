package com.laohei.nested_navigator.presentation.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.laohei.nested_navigator.app.Route

@Composable
fun MainSettingsScreen(
    settings: List<Pair<String, Route>>,
    navigateToSettingRoute: (Route) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(settings) {
            ListItem(
                modifier = Modifier.clickable {
                    navigateToSettingRoute.invoke(it.second)
                },
                headlineContent = {
                    Text(text = it.first)
                }
            )
        }
    }
}