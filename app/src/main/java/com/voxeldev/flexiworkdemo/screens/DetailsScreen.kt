package com.voxeldev.flexiworkdemo.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voxeldev.flexiworkdemo.R
import com.voxeldev.flexiworkdemo.components.BackButton
import com.voxeldev.flexiworkdemo.components.FlexiElevatedCard
import com.voxeldev.flexiworkdemo.components.PreviewBase
import com.voxeldev.flexiworkdemo.components.SelectButton
import com.voxeldev.flexiworkdemo.components.TextInfoButton
import com.voxeldev.flexiworkdemo.models.DateSelect
import com.voxeldev.flexiworkdemo.models.coworkingList
import com.voxeldev.flexiworkdemo.models.dateSelectList
import com.voxeldev.flexiworkdemo.navigation.LocalNavController

const val DEFAULT_COWORKING_ID = 0

/**
 * @author nvoxel
 * @co-author dem0nchick
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    coworkingId: Int,
) {
    val navController = LocalNavController.current

    val scrollState = rememberScrollState()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(state = rememberTopAppBarState())

    val coworkingName by rememberSaveable { mutableStateOf(coworkingList[coworkingId].name) }
    val coworkingImage by rememberSaveable { mutableIntStateOf(coworkingList[coworkingId].image) }

    Scaffold(
        modifier = Modifier
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                navigationIcon = {
                    BackButton {
                        navController.navigateUp()
                    }
                },
                title = {
                    Text(
                        text = coworkingName,
                        fontWeight = FontWeight.Bold
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
                    vertical = 8.dp
                )
                .fillMaxSize()
                .verticalScroll(state = scrollState)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 200.dp)
                    .clip(shape = RoundedCornerShape(size = 20.dp)),
                painter = painterResource(id = coworkingImage),
                contentDescription = stringResource(id = R.string.image_cowork),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(height = 16.dp))

            DateSelectList(list = dateSelectList)

            Spacer(modifier = Modifier.height(height = 16.dp))

            TextInfoButton(onClick = { /*TODO*/ }, text = "Просмотр места")

            Spacer(modifier = Modifier.height(height = 8.dp))

            TextInfoButton(onClick = { /*TODO*/ }, text = "Связаться с менеджером")

            Spacer(modifier = Modifier.height(height = 8.dp))

            TextInfoButton(onClick = { /*TODO*/ }, text = "Отмена")
        }

    }


}

@Composable
private fun DateSelectList(
    list: List<DateSelect>,
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
    ) {
        items(items = list, key = { it.id } ) {dateSelect ->
            DateSelectListItem(dataSelect = dateSelect)
        }
    }
}

@Composable
private fun DateSelectListItem(dataSelect: DateSelect) {
    FlexiElevatedCard {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SelectButton(
                text = dataSelect.selectTitle,
                onClick = {}
            )

            Spacer(modifier = Modifier.height(height = 16.dp))

            Text(
                text = dataSelect.selectDate,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )

            Spacer(modifier = Modifier.height(height = 24.dp))
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DetailsScreenPreview() {
    PreviewBase {
        DetailsScreen(coworkingId = DEFAULT_COWORKING_ID)
    }
}
