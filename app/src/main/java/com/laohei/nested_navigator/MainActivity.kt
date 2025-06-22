package com.laohei.nested_navigator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.laohei.nested_navigator.app.App
import com.laohei.nested_navigator.ui.theme.Nested_navigatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Nested_navigatorTheme {
                App()
            }
        }
    }
}