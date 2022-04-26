package dev.arcmaksim.game.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.arcmaksim.game.domain.dice.validation.DiceSizeFilter
import dev.arcmaksim.game.domain.dice.validation.DiceTypeFilter
import dev.arcmaksim.game.domain.dice.validation.DiceValueFilter
import dev.arcmaksim.presentation.theme.ComposePlaygroundTheme

@Composable
fun DiceSlot(
    modifier: Modifier = Modifier,
    sizeFilter: DiceSizeFilter? = null,
    valueFilter: DiceValueFilter? = null,
    typeFilter: DiceTypeFilter? = null,
    dieSize: Dp = 120.dp,
) {
    val backgroundColor = typeFilter?.type?.toColor() ?: Color(0xFFBBBBBB)
    val textColor = if (typeFilter == null) Color.Black else Color.White

    Box(
        modifier = modifier
            .size(dieSize)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp),
            ),
        contentAlignment = Alignment.Center,
    ) {
        if (valueFilter == null && sizeFilter == null) {
            Text(
                text = "Any",
                style = MaterialTheme.typography.h3,
                color = textColor,
            )
        } else {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                sizeFilter?.let { filter ->
                    Text(
                        text = "Only D${filter.size.value}",
                        style = MaterialTheme.typography.body1,
                        color = textColor,
                    )
                }
                valueFilter?.let { filter ->
                    Text(
                        text = filter.toExplanationString(),
                        style = MaterialTheme.typography.body1,
                        color = textColor,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoFilterDiceSlotPreview() {
    ComposePlaygroundTheme {
        DiceSlot(
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ValueFilterDiceSlotPreview() {
    ComposePlaygroundTheme {
        DiceSlot(
            modifier = Modifier.padding(16.dp),
            valueFilter = DiceValueFilter.Even,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TypeFilterDiceSlotPreview() {
    ComposePlaygroundTheme {
        DiceSlot(
            modifier = Modifier.padding(16.dp),
            typeFilter = DiceTypeFilter.CunningDiceFilter,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SizeFilterDiceSlotPreview() {
    ComposePlaygroundTheme {
        DiceSlot(
            modifier = Modifier.padding(16.dp),
            sizeFilter = DiceSizeFilter.D12SizeFilter,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AllFiltersDiceSlotPreview() {
    ComposePlaygroundTheme {
        DiceSlot(
            modifier = Modifier.padding(16.dp),
            valueFilter = DiceValueFilter.NoMoreThan(3),
            typeFilter = DiceTypeFilter.PowerDiceFilter,
            sizeFilter = DiceSizeFilter.D6SizeFilter,
        )
    }
}
