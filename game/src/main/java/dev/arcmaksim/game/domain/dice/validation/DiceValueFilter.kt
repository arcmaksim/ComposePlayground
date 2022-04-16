package dev.arcmaksim.game.domain.dice.validation

sealed class DiceValueFilter {

    object Odd : DiceValueFilter()

    object Even : DiceValueFilter()

    class NoLessThan(
        val value: Int,
    ) : DiceValueFilter()

    class NoMoreThan(
        val value: Int,
    ) : DiceValueFilter()

    class Exactly(
        val value: Int,
    ) : DiceValueFilter()

}
