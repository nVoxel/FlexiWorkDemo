package com.voxeldev.flexiworkdemo.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author nvoxel
 */
@Composable
fun JoinButton(
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 85.dp)
            .padding(all = 16.dp),
        onClick = onClick,
        shape = RoundedCornerShape(size = 20.dp)
    ) {
        Text(
            text = "Продолжить",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 18.sp,
        )
    }
}
