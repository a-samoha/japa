package com.temetnosce.japa.data.local

import com.temetnosce.japa.data.dto.ShlokaDtoList
import com.temetnosce.japa.domain.datasource.ShlokasDataSource
import com.temetnosce.japa.domain.entity.Language.English
import com.temetnosce.japa.domain.entity.SettingsKeys.language
import com.temetnosce.japa.domain.entity.Shloka
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.serialization.json.Json
import platform.Foundation.NSBundle
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.NSUserDefaults
import platform.Foundation.stringWithContentsOfFile

class ShlokasLocalDataSource : ShlokasDataSource.Local {

    @OptIn(ExperimentalForeignApi::class)
    override fun loadShlokas(): List<Shloka> {

        // Get current locale (e.g.: "en")
        val languageCode = NSUserDefaults.standardUserDefaults()
            .stringForKey(language.name) ?: English.isoFormat

        val fileName = when (languageCode) {
            "uk" -> "shlokas_uk"
            "ru" -> "shlokas_ru"
            else -> "shlokas_en"
        }

        val path = NSBundle
            .mainBundle.pathForResource(fileName, ofType = "json")
            ?: return emptyList()

        val json = NSString
            .stringWithContentsOfFile(path, encoding = NSUTF8StringEncoding, error = null)
            ?: return emptyList()

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