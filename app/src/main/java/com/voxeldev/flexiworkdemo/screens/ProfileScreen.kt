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
fun ProfileScreen() {
    Text(text = "Profile")
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProfileScreenPreview() {
    PreviewBase {
        ProfileScreen()
    }
}
