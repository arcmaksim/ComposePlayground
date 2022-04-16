package dev.arcmaksim.game.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.arcmaksim.game.CreatureFactory
import dev.arcmaksim.game.R
import dev.arcmaksim.game.domain.Creature
import dev.arcmaksim.presentation.theme.ComposePlaygroundTheme
import dev.arcmaksim.presentation.theme.Shapes
import dev.arcmaksim.presentation.theme.Typography

@Composable
fun EnemyCard(
    creature: Creature,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.aspectRatio(2f),
        shape = Shapes.medium.copy(CornerSize(16.dp)),
        backgroundColor = Color.LightGray,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = creature.name,
                style = Typography.h5,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(12.dp))
            /*Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(16.dp),
                    ),
            ) {
                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.Center),
                    bitmap = ImageBitmap.imageResource(creature.iconResId),
                    filterQuality = FilterQuality.None,
                    contentDescription = "Creature image",
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                ) {
                    repeat(creature.difficulty) {
                        Image(
                            modifier = Modifier.size(24.dp),
                            bitmap = ImageBitmap.imageResource(R.drawable.item_skull),
                            filterQuality = FilterQuality.None,
                            contentDescription = "Creature difficulty",
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))*/

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                CreatureParam(
                    paramResId = R.drawable.item_heart,
                    value = "${creature.health}",
                )
                CreatureParam(
                    paramResId = R.drawable.item_sword,
                    value = "${creature.attack}",
                )
                CreatureParam(
                    paramResId = R.drawable.item_shield,
                    value = "${creature.defence}",
                )
            }

            /*Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    textAlign = TextAlign.Center,
                    text = creature.description,
                    style = Typography.body2,
                    color = Color.Black,
                )
            }*/
        }
    }
}

@Composable
fun CreatureParam(
    @DrawableRes paramResId: Int,
    value: String,
) {
    Row(
        modifier = Modifier
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(16.dp),
            )
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            ),
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            bitmap = ImageBitmap.imageResource(paramResId),
            filterQuality = FilterQuality.None,
            contentDescription = "Creature param",
        )
        Text(
            modifier = Modifier.padding(
                start = 8.dp,
                end = 4.dp,
            ),
            text = value,
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
@Preview
fun EnemyCardPreview() {
    ComposePlaygroundTheme {
        EnemyCard(
            CreatureFactory().productSkeletonWarrior(),
        )
    }
}
