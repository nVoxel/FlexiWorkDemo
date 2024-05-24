package com.voxeldev.flexiworkdemo.screens

import android.content.res.Configuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.voxeldev.flexiworkdemo.components.PreviewBase

const val DEFAULT_COWORKING_ID = 0

/**
 * @author nvoxel
 */
@Composable
fun DetailsScreen(
    coworkingId: Int,
) {
    Text(text = "Details for $coworkingId")
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun DetailsScreenPreviewLight() {
    PreviewBase {
        DetailsScreen(coworkingId = DEFAULT_COWORKING_ID)
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DetailsScreenPreviewDark() {
    PreviewBase {
        DetailsScreen(coworkingId = DEFAULT_COWORKING_ID)
    }
}
