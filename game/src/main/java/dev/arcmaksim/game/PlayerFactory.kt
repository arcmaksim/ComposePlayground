package dev.arcmaksim.game

import dev.arcmaksim.game.domain.dice.DiceSize
import dev.arcmaksim.game.domain.Player
import dev.arcmaksim.game.domain.ability.AttackAbility
import dev.arcmaksim.game.domain.ability.HealAbility
import dev.arcmaksim.game.domain.ability.ShutterAbility

class PlayerFactory {

    val attackAbility = AttackAbility()
    val shutterAbility = ShutterAbility()
    val healAbility = HealAbility()

    fun produceDefaultPlayer(): Player = Player(
        health = 10,
        attack = 3,
        defence = 1,
        xp = 0,
        powerDice = mapOf(DiceSize.D6 to 2),
        cunningDice = mapOf(DiceSize.D6 to 1),
        mentalDice = mapOf(DiceSize.D4 to 1),
    )

}
