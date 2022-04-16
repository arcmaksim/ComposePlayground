package dev.arcmaksim.game.domain.dice.validation

import dev.arcmaksim.game.domain.dice.Dice

class DiceValidator {

    fun validateDice(
        dice: Dice,
        valueFilter: DiceValueFilter?,
        sizeFilter: DiceSizeFilter?,
        typeFilter: DiceTypeFilter?,
    ): Boolean {
        if (valueFilter == null && sizeFilter == null && typeFilter == null) return true
        valueFilter?.let { if (!validateDiceValue(dice, it)) return false }
        sizeFilter?.let { if (!validateDiceSize(dice, it)) return false }
        typeFilter?.let { if (!validateDiceType(dice, it)) return false }
        return true
    }

    private fun validateDiceValue(
        dice: Dice,
        valueFilter: DiceValueFilter,
    ): Boolean = when (valueFilter) {
        DiceValueFilter.Odd -> dice.value % 2 == 1
        DiceValueFilter.Even -> dice.value % 2 == 0
        is DiceValueFilter.NoLessThan -> dice.value >= valueFilter.value
        is DiceValueFilter.NoMoreThan -> dice.value <= valueFilter.value
        is DiceValueFilter.Exactly -> dice.value == valueFilter.value
    }

    private fun validateDiceSize(
        dice: Dice,
        sizeFilter: DiceSizeFilter,
    ): Boolean = dice.size == sizeFilter.size

    private fun validateDiceType(
        dice: Dice,
        typeFilter: DiceTypeFilter,
    ): Boolean = dice.type == typeFilter.type

}
