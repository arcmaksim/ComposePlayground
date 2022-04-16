package dev.arcmaksim.game.presentation

import androidx.compose.ui.graphics.Color
import dev.arcmaksim.game.domain.dice.DiceType

fun DiceType.toColor(): Color = when (this) {
    DiceType.POWER -> Color.Red
    DiceType.MENTAL -> Color.Blue
    DiceType.CUNNING -> Color.Green
}
