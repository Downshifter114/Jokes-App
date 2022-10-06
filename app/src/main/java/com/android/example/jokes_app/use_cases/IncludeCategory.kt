package com.android.example.jokes_app.use_cases

import com.android.example.jokes_app.model.Categories
import com.android.example.jokes_app.utils.Globals
import com.android.example.jokes_app.utils.Globals.CATEGORY

class IncludeCategory() {
    operator fun invoke(categories: Categories) {
        CATEGORY = ""
        if (!categories.programming && !categories.misc && !categories.dark && !categories.pun && !categories.spooky && !categories.christmas)
            CATEGORY = "Any"
        if (categories.programming)
            CATEGORY += "Programming,"
        if (categories.misc)
            CATEGORY += "Misc,"
        if (categories.dark)
            CATEGORY += "Dark,"
        if (categories.pun)
            CATEGORY += "Pun,"
        if (categories.spooky)
            CATEGORY += "Spooky,"
        if (categories.christmas)
            CATEGORY += "Christmas,"

        CATEGORY = CATEGORY.removeSuffix(",")
    }
}