package com.voxeldev.flexiworkdemo.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

/**
 * @author nvoxel
 */
@Composable
fun FlexiElevatedCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    OutlinedCard(
        modifier = modifier
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(size = 20.dp),
            ),
        shape = RoundedCornerShape(size = 20.dp),
        border = CardDefaults.outlinedCardBorder(enabled = false),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.background),
    ) {
        content()
    }
}
