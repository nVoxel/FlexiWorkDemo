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
import com.voxeldev.flexiworkdemo.components.PaymentList
import com.voxeldev.flexiworkdemo.components.PreviewBase
import com.voxeldev.flexiworkdemo.models.paymentListExample
import com.voxeldev.flexiworkdemo.navigation.LocalNavController
import com.voxeldev.flexiworkdemo.navigation.sharedviewmodels.ReservationsViewModel

/**
 * @author dem0nchick
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationsScreen (reservationsViewModel: ReservationsViewModel) {
    val navController = LocalNavController.current

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(state = rememberTopAppBarState())

    val reservations by reservationsViewModel.booked.observeAsState()

    reservations?.let { res ->

        val coworkers = remember(key1 = res.size) {
            res
        }

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
        ) {paddingValues ->
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
                    text = "Которые вы забронировали",
                    style = MaterialTheme.typography.titleLarge,
                )

                Spacer(modifier = Modifier.height(height = 8.dp))

                PaymentList(
                    list = res,
                    onRemoveClicked = { id -> reservationsViewModel.removeBooked(id = id) }
                )
            }
        }
    }

}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ReservationsScreenPreview() {
    val viewModel = viewModel<ReservationsViewModel>()
    viewModel.addBooked(paymentListExample[0])
    viewModel.addBooked(paymentListExample[1])
    PreviewBase {
        ReservationsScreen(reservationsViewModel =  viewModel)
    }
}