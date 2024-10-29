package com.temetnosce.japa.utils

import kotlinx.datetime.Clock

fun currentTimestamp() = Clock.System.now().toEpochMilliseconds()
