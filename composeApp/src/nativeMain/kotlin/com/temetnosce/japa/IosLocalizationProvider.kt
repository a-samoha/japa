package com.temetnosce.japa

import com.temetnosce.japa.domain.entity.Language

class IosLocalizationProvider : LocalizationProvider {
    override fun changeLang(language: Language) {
        val lang = when (language) {
            Language.EN -> "en"
            Language.UA -> "uk"
            Language.RU -> "ru"
        }
        NSUserDefaults.standardUserDefaults.setObject(arrayListOf(lang),"AppleLanguages")
    }
}