package com.temetnosce.japa

import android.content.Context
import com.temetnosce.japa.domain.entity.Language
import java.util.Locale

class AndroidLocalizationProvider(private val appContext: Context) : LocalizationProvider {

    override fun changeLang(language: Language) {
        val locale = when (language) {
            Language.EN -> Locale("en")
            Language.UA -> Locale("uk")
            Language.RU -> Locale("ru")
        }
//        val config = appContext.resources.configuration.apply {
//            setLocale(locale)
//        }
        Locale.setDefault(locale)
    }

}
