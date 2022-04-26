package dev.arcmaksim.game.presentation

import androidx.compose.ui.graphics.Color
import dev.arcmaksim.game.domain.dice.DiceType
import dev.arcmaksim.game.domain.dice.validation.DiceValueFilter

fun DiceType.toColor(): Color = when (this) {
    DiceType.POWER -> Color.Red
    DiceType.MENTAL -> Color.Blue
    DiceType.CUNNING -> Color(0xFF00BF40)
}

fun DiceValueFilter.toExplanationString(): String = when (this) {
    DiceValueFilter.Odd -> "Only odd"
    DiceValueFilter.Even -> "Only even"
    is DiceValueFilter.Exactly -> "Only $value"
    is DiceValueFilter.NoMoreThan -> "$value or more"
    is DiceValueFilter.NoLessThan -> "$value or less"
}
