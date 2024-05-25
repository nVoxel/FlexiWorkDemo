package com.voxeldev.flexiworkdemo.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
        Image(
            painter = painterResource(id = R.drawable.ic_back_button),
            contentDescription = stringResource(id = R.string.ic_back_button)
        )
    }
}