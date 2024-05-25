package com.voxeldev.flexiworkdemo.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.voxeldev.flexiworkdemo.components.CoworkingsList
import com.voxeldev.flexiworkdemo.components.PreviewBase
import com.voxeldev.flexiworkdemo.models.coworkingList
import com.voxeldev.flexiworkdemo.navigation.LocalNavController
import com.voxeldev.flexiworkdemo.navigation.NavigationScreen
import com.voxeldev.flexiworkdemo.navigation.sharedviewmodels.FavoritesViewModel

/**
 * @author nvoxel
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(favoritesViewModel: FavoritesViewModel) {
    val navController = LocalNavController.current

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(state = rememberTopAppBarState())

    val favorites by favoritesViewModel.favorites.observeAsState()

    favorites?.let { favs ->
        val coworkings = remember(key1 = favs.size) { coworkingList.filter { favs.contains(it.id) } }

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
                    text = "Которые вам нравятся",
                    style = MaterialTheme.typography.titleLarge,
                )

                Spacer(modifier = Modifier.height(height = 8.dp))

                CoworkingsList(
                    list = coworkings,
                    onItemClicked = { id ->
                        navController.navigate(NavigationScreen.Details.route!! + "/$id")
                    },
                    favorites = favs,
                    onFavoriteClicked = { id, _ -> favoritesViewModel.removeFavorite(id = id) }
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FavoritesScreenPreview() {
    val viewModel = viewModel<FavoritesViewModel>()
    viewModel.addFavorite(1)
    viewModel.addFavorite(3)
    viewModel.addFavorite(5)

    PreviewBase {
        FavoritesScreen(favoritesViewModel = viewModel)
    }
}
