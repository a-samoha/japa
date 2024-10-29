package com.temetnosce.japa

import androidx.compose.ui.window.ComposeUIViewController
import com.temetnosce.japa.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) { CommonCompose() }