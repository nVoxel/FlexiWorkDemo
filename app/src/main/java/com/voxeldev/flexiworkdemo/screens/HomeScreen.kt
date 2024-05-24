package com.voxeldev.flexiworkdemo.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voxeldev.flexiworkdemo.components.ClickableOutlinedTextField
import com.voxeldev.flexiworkdemo.components.PreviewBase
import com.voxeldev.flexiworkdemo.models.Category
import com.voxeldev.flexiworkdemo.models.Coworking
import com.voxeldev.flexiworkdemo.models.categoryList
import com.voxeldev.flexiworkdemo.models.coworkingList
import com.voxeldev.flexiworkdemo.navigation.LocalNavController
import com.voxeldev.flexiworkdemo.navigation.NavigationScreen

/**
 * @author nvoxel
 */
@Composable
fun HomeScreen() {
    val navController = LocalNavController.current
    val configuration = LocalConfiguration.current

    val scrollState = rememberScrollState()

    val coworkings = remember { coworkingList.filter { coworking -> coworking.distanceMeters <= 1000 } }

    val categories = remember { categoryList }

    Column(
        modifier = Modifier
            .verticalScroll(state = scrollState),
    ) {
        Column(
            modifier = Modifier
                .drawBehind {
                    drawRect(
                        color = Color(red = 252, green = 214, blue = 214),
                        topLeft = Offset(x = 0f, y = 0.dp.toPx()),
                        size = Size(configuration.screenWidthDp.dp.toPx(), 205.dp.toPx()),
                    )
                }
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        top = 64.dp,
                        start = 16.dp,
                        end = 16.dp,
                    ),
                text = "Найдите место в коворкинге",
                color = Color.Black,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
            )

            Spacer(modifier = Modifier.height(height = 32.dp))

            ClickableOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = "Поиск по локации или имени",
                onClick = { navController.navigate(NavigationScreen.Search.routeWithArguments) },
                trailingIcon = {
                    IconButton(onClick = { navController.navigate(NavigationScreen.Search.routeWithArguments) }) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowForward, contentDescription = "Поиск")
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                )
            )
        }

        Spacer(modifier = Modifier.height(height = 32.dp))

        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = "Места в коворкингах рядом",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(height = 16.dp))

        CoworkingList(
            list = coworkings,
            onCoworkingClicked = { id ->
                navController.navigate(NavigationScreen.Details.route!! + "/$id")
            },
        )

        Spacer(modifier = Modifier.height(height = 32.dp))

        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = "Категории",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(height = 16.dp))

        CategoryList(
            list = categories,
            onCategoryClicked = {},
        )
    }
}

@Composable
private fun CoworkingList(
    list: List<Coworking>,
    onCoworkingClicked: (Int) -> Unit,
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
    ) {
        items(
            items = list,
            key = { it.id },
        ) { coworking ->
            CoworkingListItem(
                coworking = coworking,
                onClick = { onCoworkingClicked(coworking.id) },
            )
        }
    }
}

@Composable
private fun CoworkingListItem(
    coworking: Coworking,
    onClick: () -> Unit,
) {
    OutlinedCard(
        modifier = Modifier
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(size = 20.dp),
            )
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(size = 20.dp),
        border = CardDefaults.outlinedCardBorder(enabled = false),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.background),
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 12.dp,
                    vertical = 12.dp,
                ),
        ) {
            Image(
                modifier = Modifier
                    .size(width = 200.dp, height = 100.dp)
                    .clip(shape = RoundedCornerShape(size = 20.dp)),
                painter = painterResource(id = coworking.image),
                contentDescription = "Коворкинг",
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.height(height = 8.dp))

            Text(
                modifier = Modifier
                    .width(width = 200.dp),
                text = coworking.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge,
            )

            Text(
                modifier = Modifier
                    .width(width = 200.dp),
                text = "Расстояние: ${coworking.distanceMeters} метров",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
private fun CategoryList(
    list: List<Category>,
    onCategoryClicked: (Int) -> Unit,
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
    ) {
        items(items = list, key = { it.id }) { category ->
            CategoryListItem(
                category = category,
                onClick = { onCategoryClicked(category.id) },
            )
        }
    }
}

@Composable
private fun CategoryListItem(
    category: Category,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedCard(
            modifier = Modifier
                .size(size = 70.dp)
                .shadow(
                    elevation = 3.dp,
                    shape = RoundedCornerShape(size = 20.dp),
                ),
            shape = RoundedCornerShape(size = 20.dp),
            border = CardDefaults.outlinedCardBorder(enabled = false),
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.background),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    modifier = Modifier
                        .size(size = 35.dp),
                    imageVector = category.icon,
                    contentDescription = category.title,
                )
            }
        }
        
        Spacer(modifier = Modifier.height(height = 8.dp))

        Text(
            text = category.title,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun HomeScreenPreviewLight() {
    PreviewBase {
        HomeScreen()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreviewDark() {
    PreviewBase {
        HomeScreen()
    }
}
