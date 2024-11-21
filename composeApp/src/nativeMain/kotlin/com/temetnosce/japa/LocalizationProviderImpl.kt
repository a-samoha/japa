package com.temetnosce.japa

import com.temetnosce.japa.domain.entity.Language
import platform.Foundation.NSUserDefaults

class LocalizationProviderImpl : LocalizationProvider {
    override fun changeLang(language: Language) {
        val lang = language.isoFormat
        NSUserDefaults.standardUserDefaults.setObject(arrayListOf(lang), "AppleLanguages")
    }
}