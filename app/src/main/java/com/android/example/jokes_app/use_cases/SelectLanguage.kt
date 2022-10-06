package com.android.example.jokes_app.use_cases

import com.android.example.jokes_app.utils.Globals.LANGUAGE

class SelectLanguage() {
    operator fun invoke(lang: String) {
        LANGUAGE = when (lang) {
            "de" -> "lang=de"
            "cs" -> "lang=cs"
            "es" -> "lang=es"
            "fr" -> "lang=fr"
            "pt" -> "lang=pt"
            else -> ""
        }
    }
}