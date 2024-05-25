package com.voxeldev.flexiworkdemo.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.voxeldev.flexiworkdemo.R

/**
 * @author dem0nchick
 */
@Composable
fun TextInfoButton(
    onClick: () -> Unit,
    text: String
) {
    TextButton(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(size = 20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = text,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start
            )

            Icon(
                modifier = Modifier
                    .size(width = 25.dp, height = 25.dp),
                imageVector = Icons.AutoMirrored.Default.NavigateNext,
                contentDescription = stringResource(id = R.string.ic_next_button),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }

    }
}
