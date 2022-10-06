package com.android.example.jokes_app.model

import android.graphics.drawable.ColorDrawable
import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color
import com.android.example.jokes_app.ui.theme.active
import com.android.example.jokes_app.ui.theme.passive

data class State(
    var text: String = "Bring your joke here!",
    var categoryColor: List<Color> = List(6){ passive},
    var languageColor: List<Color> = listOf(active, passive, passive, passive, passive, passive),
    var flagColor: List<Color> = List(6){ passive}
)