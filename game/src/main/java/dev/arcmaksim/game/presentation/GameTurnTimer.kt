package dev.arcmaksim.game.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun GameTurnTimer(
    remainingTime: Long,
    turnPeriod: Long,
    modifier: Modifier = Modifier,
) {
    val ratio = remainingTime / turnPeriod.toFloat()

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Cyan),
        ) {
            Unit
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(ratio)
                .background(color = Color.Blue),
        ) {
            Unit
        }
    }
}

@Preview(heightDp = 48, showBackground = true)
@Composable
fun FullGameTurnTimerPreview() {
    GameTurnTimer(
        remainingTime = 5000L,
        turnPeriod = 5000L,
    )
}

@Preview(heightDp = 48, showBackground = true)
@Composable
fun HalfGameTurnTimerPreview() {
    GameTurnTimer(
        remainingTime = 2500L,
        turnPeriod = 5000L,
    )
}
