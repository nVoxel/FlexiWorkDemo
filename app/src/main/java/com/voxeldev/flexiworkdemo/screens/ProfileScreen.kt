package com.voxeldev.flexiworkdemo.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.size.Size
import com.voxeldev.flexiworkdemo.R
import com.voxeldev.flexiworkdemo.components.PreviewBase
import com.voxeldev.flexiworkdemo.components.TextInfoButton
import com.voxeldev.flexiworkdemo.models.userExample
import com.voxeldev.flexiworkdemo.navigation.LocalNavController
import com.voxeldev.flexiworkdemo.navigation.NavigationScreen

/**
 * @author nvoxel
 * @co-author dem0nchick
 */
@Composable
fun ProfileScreen() {
    val navController = LocalNavController.current
    val configuration = LocalConfiguration.current

    val scrollState = rememberScrollState()

    val username by rememberSaveable {
        mutableStateOf(userExample.username)
    }

    val email by rememberSaveable {
        mutableStateOf(userExample.email)
    }

    val city by rememberSaveable {
        mutableStateOf(userExample.city)
    }

    val street by rememberSaveable {
        mutableStateOf(userExample.street)
    }

    val addressId by rememberSaveable {
        mutableStateOf(userExample.addressId)
    }

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)

    ) {
        Column(
            modifier = Modifier
                .drawBehind {
                    drawRect(
                        color = Color(red = 252, green = 214, blue = 214),
                        topLeft = Offset(x = 0f, y = 0.dp.toPx()),
                        size = androidx.compose.ui.geometry.Size(
                            configuration.screenWidthDp.dp.toPx(),
                            160.dp.toPx()
                        ),
                    )
                },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier
                        .padding(
                            top = 64.dp,
                        )
                        .clip(shape = RoundedCornerShape(size = 100.dp))
                        .height(height = 200.dp),
                    painter = painterResource(id = R.drawable.profile_example),
                    contentDescription = "Profile image",
                )

                Spacer(modifier = Modifier.height(height = 8.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = username,
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(height = 16.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Личная информация",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(height = 8.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "email: $email",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(height = 8.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Номер региона: $addressId",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(height = 8.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Город: $city",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(height = 8.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Улица: $street",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(height = 8.dp))

                Spacer(modifier = Modifier.height(height = 8.dp))

                TextInfoButton(onClick = {
                    navController.navigate(NavigationScreen.Reservations.routeWithArguments)
                }, text = "Мои брони")

                Spacer(modifier = Modifier.height(height = 8.dp))

                TextInfoButton(
                    onClick = {
                        navController.navigate(NavigationScreen.Welcome.routeWithArguments) {
                            popUpTo(NavigationScreen.Home.routeWithArguments) {
                                inclusive = true
                            }
                        }
                    }, text = "Выйти"
                )

            }


        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProfileScreenPreview() {
    PreviewBase {
        ProfileScreen()
    }
}
