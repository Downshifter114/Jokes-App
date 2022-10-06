package com.android.example.jokes_app.use_cases

import com.android.example.jokes_app.model.Flags
import com.android.example.jokes_app.utils.Globals.FLAG

class IncludeFlag() {
    operator fun invoke(flag: Flags) {
        FLAG = ""
        if (flag.nsfw || flag.sexist || flag.religious || flag.political || flag.racist || flag.sexist || flag.explicit)
            FLAG = "blacklistFlags="
        if (flag.nsfw)
            FLAG += "nsfw,"
        if (flag.religious)
            FLAG += "religious,"
        if (flag.political)
            FLAG += "political,"
        if (flag.racist)
            FLAG += "racist,"
        if (flag.sexist)
            FLAG += "sexist,"
        if (flag.explicit)
            FLAG += "explicit,"

        FLAG = FLAG.removeSuffix(",")
    }
}