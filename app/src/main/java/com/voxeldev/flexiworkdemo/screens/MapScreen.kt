package com.voxeldev.flexiworkdemo.screens

import android.content.res.Configuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.voxeldev.flexiworkdemo.components.PreviewBase

/**
 * @author nvoxel
 */
@Composable
fun MapScreen() {
    Text(text = "Map")
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun MapScreenPreviewLight() {
    PreviewBase {
        MapScreen()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MapScreenPreviewDark() {
    PreviewBase {
        MapScreen()
    }
}
