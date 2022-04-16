package dev.arcmaksim.game.domain.ability

import dev.arcmaksim.game.domain.dice.Dice
import dev.arcmaksim.game.domain.dice.validation.DiceSizeFilter
import dev.arcmaksim.game.domain.dice.validation.DiceTypeFilter
import dev.arcmaksim.game.domain.dice.validation.DiceValueFilter

abstract class Ability(
    val description: String,
    val valueFilter: DiceValueFilter?,
    val typeFilter: DiceTypeFilter?,
    val sizeFilter: DiceSizeFilter?,
) {

    abstract fun use(
        dice: Dice,
    ): AbilityResult

}

interface AbilityEffect

object None : AbilityEffect

class AbilityResult(
    // null means that dice was depleted
    val resultedDice: Dice?,
    val effect: AbilityEffect,
)
