package com.android.example.jokes_app.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.android.example.jokes_app.model.Categories
import com.android.example.jokes_app.model.Flags
import com.android.example.jokes_app.model.State

object Globals {
    var JOKE_STATE by mutableStateOf(State())
    var CATEGORY = "Any"
    var FLAG = ""
    var LANGUAGE = ""
    var TYPE = ""
    var ENDPOINT = "joke/"
    var CATEGORIES: Categories = Categories(
        programming = false,
        misc = false,
        dark = false,
        pun = false,
        spooky = false,
        christmas = false
    )
    var FLAGS: Flags = Flags(
        explicit = false,
        nsfw = false,
        political = false,
        racist = false,
        religious = false,
        sexist = false
    )
}