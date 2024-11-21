package com.temetnosce.japa.data.local

import android.content.Context
import com.temetnosce.japa.data.dto.ShlokaDtoList
import com.temetnosce.japa.domain.datasource.ShlokasDataSource
import com.temetnosce.japa.domain.entity.Shloka
import kotlinx.serialization.json.Json
import java.util.Locale

class ShlokasLocalDataSource(
    private val context: Context
) : ShlokasDataSource.Local {

    override fun loadShlokas(): List<Shloka> {
        val languageCode = Locale.getDefault().language

        val fileName = when (languageCode) {
            "en" -> "shlokas_en.json"
            "uk" -> "shlokas_uk.json"
            "ru" -> "shlokas_ru.json"
            else -> "shlokas_en.json"
        }

        val json = context.assets.open(fileName).bufferedReader().use { it.readText() }
        val shlokaList = Json.decodeFromString<ShlokaDtoList>(json)
        return shlokaList.shlokas.map {
            Shloka(
                id = it.id,
                shloka = it.shloka,
                synonyms = it.synonyms,
                translation = it.translation,
            )
        }
    }
}