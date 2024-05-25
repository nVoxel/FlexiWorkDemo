package com.voxeldev.flexiworkdemo.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Desk
import androidx.compose.material.icons.filled.Laptop
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voxeldev.flexiworkdemo.components.FlexiElevatedCard
import com.voxeldev.flexiworkdemo.components.PreviewBase
import com.voxeldev.flexiworkdemo.models.Coworking
import com.voxeldev.flexiworkdemo.models.coworkingList
import com.voxeldev.flexiworkdemo.navigation.LocalNavController
import com.voxeldev.flexiworkdemo.navigation.NavigationScreen

/**
 * @author nvoxel
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen() {
    val navController = LocalNavController.current

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(state = rememberTopAppBarState())

    val coworkings = remember { coworkingList }

    Scaffold(
        modifier = Modifier
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = "Коворкинги",
                        fontWeight = FontWeight.Bold,
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp,
                )
                .fillMaxSize()
        ) {
            Text(
                text = "Доступные в вашем городе",
                style = MaterialTheme.typography.titleLarge,
            )

            Spacer(modifier = Modifier.height(height = 8.dp))

            CoworkingsList(
                list = coworkings,
                onItemClicked = { id ->
                    navController.navigate(NavigationScreen.Details.route!! + "/$id")
                },
            )
        }
    }
}

@Composable
private fun CoworkingsList(
    list: List<Coworking>,
    onItemClicked: (Int) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(space = 20.dp),
    ) {
        items(items = list, key = { it.id }) { coworking ->
            CoworkingsListItem(
                coworking = coworking,
                onClick = { onItemClicked(coworking.id) }
            )
        }
    }
}

@Composable
private fun CoworkingsListItem(
    coworking: Coworking,
    onClick: () -> Unit,
) {
    FlexiElevatedCard {
        Column {
            Image(
                modifier = Modifier
                    .height(height = 180.dp)
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp
                        )
                    ),
                painter = painterResource(id = coworking.image),
                contentDescription = coworking.name,
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.height(height = 16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = coworking.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    overflow = TextOverflow.Ellipsis,
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier
                            .size(size = 20.dp),
                        imageVector = Icons.Default.Star,
                        contentDescription = "Оценка",
                    )

                    Spacer(modifier = Modifier.width(width = 4.dp))

                    Text(
                        text = "${coworking.rating}/5",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }

            Spacer(modifier = Modifier.height(height = 4.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier
                        .size(size = 16.dp),
                    imageVector = Icons.Default.Desk,
                    contentDescription = "Рабочие места",
                )

                Spacer(modifier = Modifier.width(width = 4.dp))

                Text(
                    text = coworking.workspacesCount.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                )

                Spacer(modifier = Modifier.width(width = 8.dp))

                Icon(
                    modifier = Modifier
                        .size(size = 16.dp),
                    imageVector = Icons.Default.Laptop,
                    contentDescription = "Компьютеры",
                )

                Spacer(modifier = Modifier.width(width = 4.dp))

                Text(
                    text = coworking.computersCount.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

            Spacer(modifier = Modifier.height(height = 8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row {
                    Text(
                        text = "${coworking.pricePerHour}₽",
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(modifier = Modifier.width(width = 4.dp))

                    Text(text = "в час")
                }

                OutlinedButton(
                    modifier = Modifier
                        .height(height = 35.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    contentPadding = PaddingValues(4.dp),
                    onClick = onClick,
                ) {
                    Text(
                        text = "Забронировать",
                        color = MaterialTheme.colorScheme.surface,
                    )
                }
            }

            Spacer(modifier = Modifier.height(height = 20.dp))
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ListScreenPreviewLight() {
    PreviewBase {
        ListScreen()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ListScreenPreviewDark() {
    PreviewBase {
        ListScreen()
    }
}
