package com.temetnosce.japa

import com.temetnosce.japa.domain.entity.Language
import java.util.Locale

class LocalizationProviderImpl : LocalizationProvider {

    override fun changeLang(language: Language) {
        val locale = Locale(language.isoFormat)
        Locale.setDefault(locale)
    }
}
