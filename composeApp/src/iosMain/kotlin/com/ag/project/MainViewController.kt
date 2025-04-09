package com.ag.project

import androidx.compose.ui.window.ComposeUIViewController
import com.ag.project.presentation.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    },
) {
    App()
}