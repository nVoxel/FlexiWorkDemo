package com.voxeldev.flexiworkdemo.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.voxeldev.flexiworkdemo.R

/**
 * @author dem0nchick
 */
@Composable
fun BackButton(
    onClick: () -> Unit,
) {
    TextButton(
        modifier = Modifier
            .padding(all = 8.dp),
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBackIos,
            contentDescription = stringResource(id = R.string.ic_back_button),
            tint = MaterialTheme.colorScheme.onSurface,
        )
    }
}
