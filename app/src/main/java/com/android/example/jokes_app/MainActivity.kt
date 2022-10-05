package com.android.example.jokes_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.android.example.jokes_app.Constants.JOKE_STATE
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SimpleMonitor(state = JOKE_STATE)

        }
    }
}

fun getJoke(jokeType: String) {
    GlobalScope.launch {
        val api = JokeRepositoryImpl().getInstance().create(JokesApi::class.java)
        JOKE_STATE = try {
            if (jokeType == "single")
                JOKE_STATE.copy(text = api.getSingleJoke()!!.joke)
            else {
                val joke = api.getTwoPartJoke()
                JOKE_STATE.copy(text = "-" + joke!!.setup + "\n-" + joke!!.delivery)
            }
        } catch (e: Exception) {
            JOKE_STATE.copy(text = "Something went wrong.")
        }
    }
}

@Composable
fun SimpleMonitor(state: State) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = state.text, fontSize = 36.sp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { getJoke(jokeType = "single") }) {
                Text(text = "Single Joke")
            }
            Button(onClick = { getJoke(jokeType = "twopart") }) {
                Text(text = "Two-part Joke")
            }
        }
    }
}

