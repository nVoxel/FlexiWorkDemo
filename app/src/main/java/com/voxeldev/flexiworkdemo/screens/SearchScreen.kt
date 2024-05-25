package com.voxeldev.flexiworkdemo.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voxeldev.flexiworkdemo.components.FlexiElevatedCard
import com.voxeldev.flexiworkdemo.components.PreviewBase
import com.voxeldev.flexiworkdemo.models.facilityData
import com.voxeldev.flexiworkdemo.models.placeTypeData
import com.voxeldev.flexiworkdemo.models.serviceData
import com.voxeldev.flexiworkdemo.navigation.LocalNavController
import com.voxeldev.flexiworkdemo.navigation.NavigationScreen

/**
 * @author nvoxel
 */
@Composable
fun SearchScreen() {
    val navController = LocalNavController.current

    val scrollState = rememberScrollState()

    var searchText by rememberSaveable { mutableStateOf("") }

    val priceRangeSaver = remember {
        Saver<ClosedFloatingPointRange<Float>, FloatArray>(
            save = { floatArrayOf(it.start, it.endInclusive) },
            restore = { it[0]..it[1] },
        )
    }
    val priceRange = rememberSaveable(saver = priceRangeSaver) { 1000f..5000f }

    val priceRangeSelectedSaver = remember {
        Saver<MutableState<ClosedFloatingPointRange<Float>>, FloatArray>(
            save = { floatArrayOf(it.value.start, it.value.endInclusive) },
            restore = { mutableStateOf(it[0]..it[1]) },
        )
    }
    var priceRangeSelected by rememberSaveable(saver = priceRangeSelectedSaver) { mutableStateOf(1000f..5000f) }

    var desksCount by rememberSaveable { mutableIntStateOf(0) }
    val maxDesksCount = remember { 5 }

    var meetingRoomsCount by rememberSaveable { mutableIntStateOf(0) }
    val maxMeetingRoomsCount = remember { 5 }

    val types = remember { placeTypeData }
    var selectedType by rememberSaveable { mutableIntStateOf(0) }

    val facilitiesList = rememberSaveable { facilityData }
    val facilitiesSelection = remember {
        List(size = facilitiesList.size) { false }.toMutableStateList()
    }

    val servicesList = rememberSaveable { serviceData }
    val servicesSelection = remember {
        List(size = servicesList.size) { false }.toMutableStateList()
    }

    Column(
        modifier = Modifier
            .verticalScroll(state = scrollState)
            .padding(horizontal = 16.dp),
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp),
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text(text = "Поиск по локации или имени") },
            trailingIcon = {
                IconButton(onClick = { navController.navigate(NavigationScreen.List.routeWithArguments) }) {
                    Icon(imageVector = Icons.Default.FilterAlt, contentDescription = "Поиск")
                }
            },
            shape = RoundedCornerShape(size = 32.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        )

        Spacer(modifier = Modifier.height(height = 32.dp))

        FlexiElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(all = 16.dp)
            ) {
                Text(
                    text = "Диапазон цен",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(height = 8.dp))

                RangeSlider(
                    value = priceRangeSelected,
                    onValueChange = { priceRangeSelected = it },
                    valueRange = priceRange,
                    steps = 30,
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Мин: ${priceRangeSelected.start.toInt()} руб./сут.",
                        style = MaterialTheme.typography.bodyMedium,
                    )

                    Text(
                        text = "Макс: ${priceRangeSelected.endInclusive.toInt()} руб./сут.",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(height = 32.dp))

        FlexiElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(all = 16.dp)
            ) {
                Text(
                    text = "Вместимость",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(height = 16.dp))

                Text(text = "Рабочие столы")

                Spacer(modifier = Modifier.height(height = 8.dp))

                ButtonsList(
                    selectedButton = desksCount,
                    buttonsCount = maxDesksCount,
                    onClick = { desksCount = it },
                )

                Spacer(modifier = Modifier.height(height = 16.dp))

                Text(text = "Переговорные")

                Spacer(modifier = Modifier.height(height = 8.dp))

                ButtonsList(
                    selectedButton = meetingRoomsCount,
                    buttonsCount = maxMeetingRoomsCount,
                    onClick = { meetingRoomsCount = it },
                )
            }
        }

        Spacer(modifier = Modifier.height(height = 32.dp))

        FlexiElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(all = 16.dp)
            ) {
                Text(
                    text = "Тип",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                )

                ButtonsRow(
                    selectedButtonIndex = selectedType,
                    buttonLabels = types,
                    onClick = { selectedType = it },
                )
            }
        }

        Spacer(modifier = Modifier.height(height = 32.dp))

        FlexiElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(all = 16.dp)
            ) {
                Text(
                    text = "Удобства",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(height = 16.dp))

                CheckboxList(
                    labels = facilitiesList,
                    selection = facilitiesSelection,
                    onChecked = { index, checked ->
                        facilitiesSelection[index] = checked
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(height = 32.dp))

        FlexiElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(all = 16.dp)
            ) {
                Text(
                    text = "Сервисы",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(height = 16.dp))

                CheckboxList(
                    labels = servicesList,
                    selection = servicesSelection,
                    onChecked = { index, checked ->
                        servicesSelection[index] = checked
                        println(servicesSelection[index])
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(height = 16.dp))

        BottomRow(
            onClearClick = {
                searchText = ""
                priceRangeSelected = priceRange
                desksCount = 0
                meetingRoomsCount = 0
                selectedType = 0
                clearList(list = facilitiesSelection)
                clearList(list = servicesSelection)
            },
            onApplyClick = { navController.navigate(NavigationScreen.List.routeWithArguments) },
        )

        Spacer(modifier = Modifier.height(height = 32.dp))
    }
}

@Composable
private fun ButtonsList(
    selectedButton: Int,
    buttonsCount: Int,
    onClick: (Int) -> Unit,
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(space = 8.dp)) {
        items(count = buttonsCount, key = { it }) { count ->
            OutlinedButton(
                modifier = Modifier
                    .width(width = if (count == 0) 70.dp else 50.dp)
                    .height(height = 50.dp),
                shape = CircleShape,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (count == selectedButton) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (count == selectedButton) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                ),
                contentPadding = PaddingValues(0.dp),
                onClick = { onClick(count) },
            ) {
                Text(
                    text = if (count == 0) "Любое" else count.toString(),
                    color = if (count == selectedButton) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ButtonsRow(
    selectedButtonIndex: Int,
    buttonLabels: List<String>,
    onClick: (Int) -> Unit,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
    ) {
        buttonLabels.forEachIndexed { index, label ->
            OutlinedButton(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(width = 150.dp, height = 50.dp),
                shape = CircleShape,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (index == selectedButtonIndex) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (index == selectedButtonIndex) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                ),
                contentPadding = PaddingValues(0.dp),
                onClick = { onClick(index) },
            ) {
                Text(
                    text = buttonLabels[index],
                    color = if (index == selectedButtonIndex) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

@Composable
private fun CheckboxList(
    labels: List<String>,
    selection: List<Boolean>,
    onChecked: (Int, Boolean) -> Unit,
) {
    labels.forEachIndexed { index, label ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onChecked(index, !selection[index]) },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = selection[index],
                onCheckedChange = { onChecked(index, it) },
            )

            Text(text = label)
        }
    }
}

@Composable
private fun BottomRow(
    onClearClick: () -> Unit,
    onApplyClick: () -> Unit,
) {
    Row {
        OutlinedButton(
            modifier = Modifier
                .padding(top = 16.dp)
                .size(width = 150.dp, height = 50.dp),
            shape = CircleShape,
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface
            ),
            contentPadding = PaddingValues(0.dp),
            onClick = onClearClick,
        ) {
            Text(
                text = "Сбросить",
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

        Spacer(modifier = Modifier.width(width = 16.dp))

        OutlinedButton(
            modifier = Modifier
                .padding(top = 16.dp)
                .height(height = 50.dp)
                .weight(weight = 1f),
            shape = CircleShape,
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary
            ),
            contentPadding = PaddingValues(0.dp),
            onClick = onApplyClick,
        ) {
            Text(
                text = "Применить фильтры",
                color = MaterialTheme.colorScheme.surface,
            )
        }
    }
}

private fun clearList(list: SnapshotStateList<Boolean>) {
    for (i in list.indices) {
        list[i] = false
    }
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
