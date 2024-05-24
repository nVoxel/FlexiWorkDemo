package com.voxeldev.flexiworkdemo.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

val DEFAULT_COWORKING_ID = 0

/**
 * @author nvoxel
 */
@Composable
fun DetailsScreen(
    coworkingId: Int,
) {
    Text(text = "Details for $coworkingId")
}
