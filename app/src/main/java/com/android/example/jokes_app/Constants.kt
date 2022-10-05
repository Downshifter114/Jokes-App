package com.android.example.jokes_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object Constants {
    var JOKE_STATE by mutableStateOf(State())
}