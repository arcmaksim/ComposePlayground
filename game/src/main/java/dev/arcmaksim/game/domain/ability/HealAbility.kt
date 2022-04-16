package dev.arcmaksim.game.domain.ability

import dev.arcmaksim.game.domain.dice.Dice

class HealAbility : Ability(
    description = "Heals player with Dice / 2",
    valueFilter = null,
    typeFilter = null,
    sizeFilter = null,
) {

    override fun use(
        dice: Dice,
    ): AbilityResult = AbilityResult(
        resultedDice = dice.divideValue(2),
        effect = None,
    )

}
