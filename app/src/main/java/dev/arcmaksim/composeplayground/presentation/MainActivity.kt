package dev.arcmaksim.composeplayground.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.arcmaksim.composeplayground.presentation.ui.theme.ComposePlaygroundTheme
import dev.arcmaksim.composeplayground.presentation.ui.yogawave.YogaWaveScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                YogaWaveScreen()
            }
        }
    }
}
