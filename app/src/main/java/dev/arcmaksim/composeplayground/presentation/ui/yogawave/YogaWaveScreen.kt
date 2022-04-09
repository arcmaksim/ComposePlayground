package dev.arcmaksim.composeplayground.presentation.ui.yogawave

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.arcmaksim.composeplayground.presentation.ui.theme.ComposePlaygroundTheme

@Composable
fun YogaWaveScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {
        Column {
            YogaWaveCourseCard(
                currentProgress = 5,
                totalProgress = 14,
                category = "Weight Loss",
                title = "First Class",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun YogaWaveScreenPreview() {
    ComposePlaygroundTheme {
        YogaWaveScreen()
    }
}
