package dev.arcmaksim.game.domain.dice.validation

import dev.arcmaksim.game.domain.dice.DiceType

class DiceTypeFilter private constructor(
    val type: DiceType,
) {

    companion object {

        val PowerDiceFilter = DiceTypeFilter(DiceType.POWER)

        val CunningDiceFilter = DiceTypeFilter(DiceType.CUNNING)

        val MentalDiceFilter = DiceTypeFilter(DiceType.MENTAL)

    }

}
