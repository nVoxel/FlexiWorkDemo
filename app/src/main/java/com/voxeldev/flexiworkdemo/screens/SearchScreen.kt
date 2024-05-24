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
fun SearchScreen() {
    Text(text = "Search")
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun SearchScreenPreviewLight() {
    PreviewBase {
        SearchScreen()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SearchScreenPreviewDark() {
    PreviewBase {
        SearchScreen()
    }
}
