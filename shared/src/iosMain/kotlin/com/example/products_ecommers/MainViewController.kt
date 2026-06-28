package com.example.products_ecommers

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController { App(
    @Composable
    fun Greeting(names: List<String>) {
        for (name in names) {
            Text ("Hello $name")
        }
    }
)
}