package dev.arcmaksim.game.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.arcmaksim.game.R
import dev.arcmaksim.presentation.theme.ComposePlaygroundTheme
import dev.arcmaksim.presentation.theme.Shapes
import dev.arcmaksim.presentation.theme.Typography

@Composable
fun GameCard(
    title: String,
    @DrawableRes icon: Int,
    description: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.aspectRatio(.75f),
        shape = Shapes.medium.copy(CornerSize(16.dp)),
        contentColor = Color.LightGray,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = title,
                style = Typography.h5,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(16.dp),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    modifier = Modifier.size(80.dp),
                    bitmap = ImageBitmap.imageResource(icon),
                    filterQuality = FilterQuality.None,
                    contentDescription = "Card Image",
                )
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = description,
                    style = Typography.h5,
                    color = Color.Black,
                )
            }
        }
    }
}

@Composable
@Preview
fun GameCardPreview() {
    ComposePlaygroundTheme {
        GameCard(
            title = "Enemy",
            icon = R.drawable.creature_skeleton_warrior,
            description = "Regular skeleton",
        )
    }
}
