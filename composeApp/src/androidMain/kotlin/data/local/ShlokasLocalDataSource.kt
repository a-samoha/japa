package data.local

import android.content.Context
import data.dto.ShlokaList
import domain.entity.Shloka
import kotlinx.serialization.json.Json

actual class ShlokasLocalDataSource(
    private val context: Context
) {

    actual fun loadShlokas(): List<Shloka> {
        val json = context.assets.open("shlokas_en.json").bufferedReader().use { it.readText() }
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