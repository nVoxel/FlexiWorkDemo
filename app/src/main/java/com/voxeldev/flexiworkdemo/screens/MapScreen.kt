package com.voxeldev.flexiworkdemo.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voxeldev.flexiworkdemo.R
import com.voxeldev.flexiworkdemo.components.PreviewBase
import com.voxeldev.flexiworkdemo.navigation.LocalNavController
import com.voxeldev.flexiworkdemo.navigation.NavigationScreen

/**
 * @author nvoxel
 */
@Composable
fun MapScreen() {
    val navController = LocalNavController.current

    var titleSearch by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = titleSearch,
            onValueChange = { titleSearch = it },
            placeholder = {
                Text(
                    overflow = TextOverflow.Ellipsis,
                    text = "Введите пункт назначения",
                )
            },
            trailingIcon = {
                if (titleSearch.isNotEmpty()) {
                    IconButton(onClick = { navController.navigate(NavigationScreen.List.routeWithArguments) }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Поиск")
                    }
                }
            },
            shape = RoundedCornerShape(size = 32.dp)
        )

        ListButton(
            onClick = { navController.navigate(NavigationScreen.List.routeWithArguments) }
        )

        Image(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(size = 20.dp)),
            painter = painterResource(id = R.drawable.vk_map_example),
            contentDescription = "VK maps",
            contentScale = ContentScale.Crop,
        )

    }

}

@Composable
private fun ListButton(
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth(),
        onClick = onClick,
    ) {
        Text(
            text = "Список мест",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 18.sp,
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MapScreenPreview() {
    PreviewBase {
        MapScreen()
    }
}
