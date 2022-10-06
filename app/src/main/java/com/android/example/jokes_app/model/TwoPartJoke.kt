package com.android.example.jokes_app.model

import com.android.example.jokes_app.model.Flags

data class TwoPartJoke(
    val category: String,
    val error: Boolean,
    val flags: Flags,
    val id: Int,
    val setup: String,
    val delivery: String,
    val lang: String,
    val safe: Boolean,
    val type: String
)
