package dev.arcmaksim.game.domain.ability

import dev.arcmaksim.game.domain.dice.Dice

class AttackAbility : Ability(
    description = "Attack enemy with placed dice",
    valueFilter = null,
    typeFilter = null,
    sizeFilter = null,
) {

    override fun use(
        dice: Dice,
    ): AbilityResult = AbilityResult(
        resultedDice = dice.subtractValue(2),
        effect = None,
    )

}
