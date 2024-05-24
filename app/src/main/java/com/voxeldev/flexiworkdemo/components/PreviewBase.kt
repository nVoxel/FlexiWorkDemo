package com.voxeldev.flexiworkdemo.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.voxeldev.flexiworkdemo.navigation.LocalNavController
import com.voxeldev.flexiworkdemo.theme.FlexiWorkDemoTheme

/**
 * @author nvoxel
 */
@Composable
fun PreviewBase(previewContent: @Composable () -> Unit) {
    CompositionLocalProvider(LocalNavController provides NavHostController(LocalContext.current)){
        FlexiWorkDemoTheme {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                previewContent()
            }
        }
    }
}
