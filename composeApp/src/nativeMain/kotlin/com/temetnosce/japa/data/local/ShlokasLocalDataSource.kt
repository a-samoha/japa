package com.temetnosce.japa.data.local

import com.temetnosce.japa.data.dto.ShlokaList
import com.temetnosce.japa.domain.datasource.ShlokasDataSource
import com.temetnosce.japa.domain.entity.Shloka
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.serialization.json.Json
import platform.Foundation.NSBundle
import platform.Foundation.NSString
import platform.Foundation.stringWithContentsOfFile
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.localeIdentifier
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale

class ShlokasLocalDataSource : ShlokasDataSource.Local {

    @OptIn(ExperimentalForeignApi::class)
    override fun loadShlokas(): List<Shloka> {
        val languageCode =
            NSLocale.currentLocale().localeIdentifier.take(2) // Получаем код языка (например, "en")
        val fileName = when (languageCode) {
            "uk" -> "shlokas_ua"
            "ru" -> "shlokas_ru"
            else -> "shlokas_en"
        }

        val path = NSBundle.mainBundle.pathForResource(fileName, ofType = "json")
        if (path == null) {
            println("Файл $fileName.json не найден.")
            return emptyList()
        }

        val json =
            NSString.stringWithContentsOfFile(path, encoding = NSUTF8StringEncoding, error = null)
                ?: return emptyList()
        val shlokaList = Json.decodeFromString<ShlokaList>(json)
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