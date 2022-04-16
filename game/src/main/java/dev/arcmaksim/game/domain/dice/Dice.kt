package dev.arcmaksim.game.domain.dice

data class Dice(
    val value: Int,
    val size: DiceSize,
    val type: DiceType,
) {

    constructor(
        size: DiceSize,
        type: DiceType,
    ) : this(
        size = size,
        type = type,
        value = size.value,
    )

    init {
        require(value >= 1 && value <= size.value) {
            "Dice value is incorrect: $value - should be in 1..${size.value}"
        }
    }

    fun subtractValue(
        valueToSubtract: Int,
    ): Dice? {
        val subtractedValue = value - valueToSubtract
        return if (subtractedValue < 1) {
            null
        } else {
            copy(
                value = subtractedValue,
            )
        }
    }

    fun addValue(
        valueToAdd: Int,
    ): Dice = copy(
        value = (value + valueToAdd).coerceAtMost(size.value),
    )

    fun multiplyValue(
        multiplier: Int,
    ): Dice = copy(
        value = (value * multiplier).coerceAtMost(size.value),
    )

    fun divideValue(
        divider: Int,
    ): Dice = copy(
        value = (value / divider).coerceAtLeast(1),
    )

    fun setValue(
        value: Int,
    ): Dice = copy(
        value = value.coerceIn(1, size.value),
    )

}
