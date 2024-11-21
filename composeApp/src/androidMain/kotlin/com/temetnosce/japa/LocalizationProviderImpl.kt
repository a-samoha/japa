package com.temetnosce.japa

import com.temetnosce.japa.domain.entity.Language
import java.util.Locale

class LocalizationProviderImpl() : LocalizationProvider {

    override fun changeLang(language: Language) {
        val locale = when (language) {
            Language.EN -> Locale("en")
            Language.UA -> Locale("uk")
            Language.RU -> Locale("ru")
        }
        Locale.setDefault(locale)
    }
}
