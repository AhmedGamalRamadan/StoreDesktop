package com.ag.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.ag.project.presentation.di.initKoin

fun main() = application {

    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "FakeStoreApp",
    ) {
        App()
    }
}