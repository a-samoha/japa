package com.temetnosce.japa.data.local

import android.content.Context
import com.temetnosce.japa.domain.datasource.ChantedRoundsDataSource

actual class ChantedRoundsLocalDataSource(
    private val context: Context
) : ChantedRoundsDataSource.Local