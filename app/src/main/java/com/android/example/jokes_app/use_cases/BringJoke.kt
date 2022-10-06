package com.android.example.jokes_app.use_cases

import android.util.Log
import com.android.example.jokes_app.data.JokeRepositoryImpl
import com.android.example.jokes_app.data.JokesApi
import com.android.example.jokes_app.utils.Globals
import com.android.example.jokes_app.utils.Globals.CATEGORY
import com.android.example.jokes_app.utils.Globals.ENDPOINT
import com.android.example.jokes_app.utils.Globals.FLAG
import com.android.example.jokes_app.utils.Globals.JOKE_STATE
import com.android.example.jokes_app.utils.Globals.LANGUAGE
import com.android.example.jokes_app.utils.Globals.TYPE
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class BringJoke() {
    operator fun invoke(type: Int) {
        GlobalScope.launch {
            val api = JokeRepositoryImpl().getInstance().create(JokesApi::class.java)
            when (type) {
                0 -> {
                    TYPE = "type=single"
                    ENDPOINT = "joke/$CATEGORY?$LANGUAGE&$FLAG&$TYPE"
                    JOKE_STATE = try {
                        val joke = api.getSingleJoke(ENDPOINT)
                        JOKE_STATE.copy(text = "-" + joke!!.joke)
                    } catch (e: Exception) {
                        JOKE_STATE.copy(text = "Sorry, we couldn't find any joke with your filters.")
                    }
                }
                1 -> {
                    TYPE = "type=twopart"
                    ENDPOINT = "joke/$CATEGORY?$LANGUAGE&$FLAG&$TYPE"
                    JOKE_STATE = try {
                        val joke = api.getTwoPartJoke(ENDPOINT)
                        JOKE_STATE.copy(text = "+${joke!!.setup}\n\n-${joke!!.delivery}")
                    } catch (e: Exception) {
                        JOKE_STATE.copy(text = "Sorry, we couldn't find any joke with your filters.")
                    }
                }
                else -> JOKE_STATE = JOKE_STATE.copy(text = "Something went wrong.")
            }
        }
    }
}