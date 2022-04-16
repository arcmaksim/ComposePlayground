package dev.arcmaksim.composeplayground.presentation.ui.draganddrop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import dev.arcmaksim.composeplayground.presentation.ui.draganddrop.model.Die
import dev.arcmaksim.composeplayground.presentation.ui.draganddrop.model.DieValue
import dev.arcmaksim.presentation.theme.ComposePlaygroundTheme

@Composable
fun Die(
    die: Die?,
    modifier: Modifier = Modifier,
    dieSize: Dp = 120.dp,
) {
    Box(
        modifier = modifier
            .size(dieSize)
            .background(
                color = Color(0xFFBBBBBB),
                shape = RoundedCornerShape(16.dp),
            ),
        contentAlignment = Alignment.Center,
    ) {
        die?.let {
            Text(
                text = it.value.value.toString(),
                style = MaterialTheme.typography.h2,
                color = Color.Black,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiePreview() {
    ComposePlaygroundTheme {
        Die(
            modifier = Modifier.padding(16.dp),
            die = Die(DieValue.Five),
        )
    }
}
