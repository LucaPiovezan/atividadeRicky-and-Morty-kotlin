package com.luca.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.luca.myapplication.ui.characters.CharactersScreen
import com.luca.myapplication.ui.theme.AtividadeRickyAndMortyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AtividadeRickyAndMortyTheme {
                CharactersScreen()
            }
        }
    }
}
