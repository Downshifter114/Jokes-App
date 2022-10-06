package com.android.example.jokes_app

import android.content.pm.ActivityInfo
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.example.jokes_app.utils.Globals.JOKE_STATE
import com.android.example.jokes_app.data.JokeRepositoryImpl
import com.android.example.jokes_app.data.JokesApi
import com.android.example.jokes_app.model.Categories
import com.android.example.jokes_app.model.Flags
import com.android.example.jokes_app.model.State
import com.android.example.jokes_app.ui.theme.active
import com.android.example.jokes_app.ui.theme.passive
import com.android.example.jokes_app.use_cases.BringJoke
import com.android.example.jokes_app.use_cases.IncludeCategory
import com.android.example.jokes_app.use_cases.IncludeFlag
import com.android.example.jokes_app.use_cases.SelectLanguage
import com.android.example.jokes_app.utils.Globals.CATEGORIES
import com.android.example.jokes_app.utils.Globals.FLAG
import com.android.example.jokes_app.utils.Globals.FLAGS
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT
        super.onCreate(savedInstanceState)
        setContent {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                //Language section
                LanguageSection(modifier = Modifier)
                //Categories section
                CategoriesSection(modifier = Modifier)
                //Flags section
                FlagsSection(modifier = Modifier)
                Box(modifier = Modifier
                    .weight(.5f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .border(2.dp, Color.Black, shape = RoundedCornerShape(10.dp))
                    .background(Color.LightGray)) {
                    //Joke section
                    JokeSection(modifier = Modifier.align(Alignment.Center))
                }
                Box(modifier = Modifier.weight(.120f)) {
                    //Bring joke section
                    BringJokeSection(modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun CategoriesSection(modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Include/Exclude Category")
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Button(
                onClick = {
                    CATEGORIES.programming = !CATEGORIES.programming;
                    IncludeCategory().invoke(CATEGORIES);
                    val tempList = JOKE_STATE.categoryColor.toMutableList()
                    tempList[0] = if (tempList[0] == passive) active else passive
                    JOKE_STATE = JOKE_STATE.copy(categoryColor = tempList)
            },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = JOKE_STATE.categoryColor[0])
            ) {
                Text(text = "Coding")
            }
            Button(onClick = {
                CATEGORIES.misc = !CATEGORIES.misc; IncludeCategory().invoke(CATEGORIES);
                val tempList = JOKE_STATE.categoryColor.toMutableList()
                tempList[1] = if (tempList[1] == passive) active else passive
                JOKE_STATE = JOKE_STATE.copy(categoryColor = tempList) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = JOKE_STATE.categoryColor[1])
            ) {
                Text(text = "Misc")
            }
            Button(onClick = {
                CATEGORIES.dark = !CATEGORIES.dark; IncludeCategory().invoke(CATEGORIES);
                val tempList = JOKE_STATE.categoryColor.toMutableList()
                tempList[2] = if (tempList[2] == passive) active else passive
                JOKE_STATE = JOKE_STATE.copy(categoryColor = tempList) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = JOKE_STATE.categoryColor[2])
            ) {
                Text(text = "Dark")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Button(onClick = {
                CATEGORIES.pun = !CATEGORIES.pun; IncludeCategory().invoke(CATEGORIES);
                val tempList = JOKE_STATE.categoryColor.toMutableList()
                tempList[3] = if (tempList[3] == passive) active else passive
                JOKE_STATE = JOKE_STATE.copy(categoryColor = tempList) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = JOKE_STATE.categoryColor[3])
            ) {
                Text(text = "Pun")
            }
            Button(onClick = {
                CATEGORIES.spooky = !CATEGORIES.spooky; IncludeCategory().invoke(CATEGORIES);
                val tempList = JOKE_STATE.categoryColor.toMutableList()
                tempList[4] = if (tempList[4] == passive) active else passive
                JOKE_STATE = JOKE_STATE.copy(categoryColor = tempList) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = JOKE_STATE.categoryColor[4])
            ) {
                Text(text = "Spooky")
            }
            Button(onClick = {
                CATEGORIES.christmas = !CATEGORIES.christmas; IncludeCategory().invoke(CATEGORIES);
                val tempList = JOKE_STATE.categoryColor.toMutableList()
                tempList[5] = if (tempList[5] == passive) active else passive
                JOKE_STATE = JOKE_STATE.copy(categoryColor = tempList) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = JOKE_STATE.categoryColor[5])
            ) {
                Text(text = "Christmas")
            }
        }
    }
}

@Composable
fun LanguageSection(modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = "Select Language")
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Button(onClick = { SelectLanguage().invoke("en")
                val tempList = MutableList(6){ passive}
                tempList[0] = active
                JOKE_STATE = JOKE_STATE.copy(languageColor = tempList) }, modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = JOKE_STATE.languageColor[0])) {
                Text(text = "EN")
            }
            Button(onClick = { SelectLanguage().invoke("cs")
                val tempList = MutableList(6){ passive}
                tempList[1] = active
                JOKE_STATE = JOKE_STATE.copy(languageColor = tempList) }, modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = JOKE_STATE.languageColor[1])) {
                Text(text = "CS")
            }
            Button(onClick = { SelectLanguage().invoke("de")
                val tempList = MutableList(6){ passive}
                tempList[2] = active
                JOKE_STATE = JOKE_STATE.copy(languageColor = tempList) }, modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = JOKE_STATE.languageColor[2])) {
                Text(text = "DE")
            }
            Button(onClick = { SelectLanguage().invoke("es")
                val tempList = MutableList(6){ passive}
                tempList[3] = active
                JOKE_STATE = JOKE_STATE.copy(languageColor = tempList) }, modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = JOKE_STATE.languageColor[3])) {
                Text(text = "ES")
            }
            Button(onClick = { SelectLanguage().invoke("fr")
                val tempList = MutableList(6){ passive}
                tempList[4] = active
                JOKE_STATE = JOKE_STATE.copy(languageColor = tempList) }, modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = JOKE_STATE.languageColor[4])) {
                Text(text = "FR")
            }
            Button(onClick = { SelectLanguage().invoke("pt")
                val tempList = MutableList(6){ passive}
                tempList[5] = active
                JOKE_STATE = JOKE_STATE.copy(languageColor = tempList) }, modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = JOKE_STATE.languageColor[5])) {
                Text(text = "PT")
            }
        }
    }  
}

@Composable
fun FlagsSection(modifier: Modifier) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Include/Exclude Flags (Jokes you don't want to hear about)")
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Button(
                onClick = {
                    FLAGS.nsfw = !FLAGS.nsfw; IncludeFlag().invoke(FLAGS)
                    val tempList = JOKE_STATE.flagColor.toMutableList()
                    tempList[0] = if (tempList[0] == passive) active else passive
                    JOKE_STATE = JOKE_STATE.copy(flagColor = tempList)
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = JOKE_STATE.flagColor[0])
            ) {
                Text(text = "Nsfw")
            }
            Button(
                onClick = {
                    FLAGS.religious = !FLAGS.religious; IncludeFlag().invoke(FLAGS)
                    val tempList = JOKE_STATE.flagColor.toMutableList()
                    tempList[1] = if (tempList[1] == passive) active else passive
                    JOKE_STATE = JOKE_STATE.copy(flagColor = tempList)
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = JOKE_STATE.flagColor[1])
            ) {
                Text(text = "Religious")
            }
            Button(
                onClick = {
                    FLAGS.political = !FLAGS.political; IncludeFlag().invoke(FLAGS)
                    val tempList = JOKE_STATE.flagColor.toMutableList()
                    tempList[2] = if (tempList[2] == passive) active else passive
                    JOKE_STATE = JOKE_STATE.copy(flagColor = tempList)
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = JOKE_STATE.flagColor[2])
            ) {
                Text(text = "Political")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Button(
                onClick = {
                    FLAGS.racist = !FLAGS.racist; IncludeFlag().invoke(FLAGS)
                    val tempList = JOKE_STATE.flagColor.toMutableList()
                    tempList[3] = if (tempList[3] == passive) active else passive
                    JOKE_STATE = JOKE_STATE.copy(flagColor = tempList)
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = JOKE_STATE.flagColor[3])
            ) {
                Text(text = "Racist")
            }
            Button(
                onClick = {
                    FLAGS.sexist = !FLAGS.sexist; IncludeFlag().invoke(FLAGS)
                    val tempList = JOKE_STATE.flagColor.toMutableList()
                    tempList[4] = if (tempList[4] == passive) active else passive
                    JOKE_STATE = JOKE_STATE.copy(flagColor = tempList)
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = JOKE_STATE.flagColor[4])
            ) {
                Text(text = "Sexist")
            }
            Button(
                onClick = {
                    FLAGS.explicit = !FLAGS.explicit; IncludeFlag().invoke(FLAGS)
                    val tempList = JOKE_STATE.flagColor.toMutableList()
                    tempList[5] = if (tempList[5] == passive) active else passive
                    JOKE_STATE = JOKE_STATE.copy(flagColor = tempList)
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = JOKE_STATE.flagColor[5])
            ) {
                Text(text = "Explicit")
            }
        }
    }
}

@Composable
fun JokeSection(modifier: Modifier) {
    Text(text = JOKE_STATE.text, modifier = modifier.padding(horizontal = 10.dp), fontSize = 15.sp,fontWeight = FontWeight.Bold, textAlign = TextAlign.Justify)
}

@Composable
fun BringJokeSection(modifier: Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Button(onClick = { BringJoke().invoke(0) }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray) ) {
            Text(text = "Single-Type\nJoke", textAlign = TextAlign.Center)
        }
        Button(onClick = { BringJoke().invoke(1) }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)) {
            Text(text = "TwoPart-Type\nJoke", textAlign = TextAlign.Center)
        }
    }
}









