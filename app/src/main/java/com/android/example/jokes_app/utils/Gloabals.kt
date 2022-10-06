package com.android.example.jokes_app.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.android.example.jokes_app.model.State

object Constants {
    var JOKE_STATE by mutableStateOf(State())
}