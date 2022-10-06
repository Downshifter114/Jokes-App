package com.android.example.jokes_app.model

data class Flags(
    var explicit: Boolean = false,
    var nsfw: Boolean = false,
    var political: Boolean = false,
    var racist: Boolean = false,
    var religious: Boolean = false,
    var sexist: Boolean = false
)