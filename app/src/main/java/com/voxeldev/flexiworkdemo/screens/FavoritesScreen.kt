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
fun FavoritesScreen() {
    Text(text = "Favorites")
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun FavoritesScreenPreviewLight() {
    PreviewBase {
        FavoritesScreen()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FavoritesScreenPreviewDark() {
    PreviewBase {
        FavoritesScreen()
    }
}
