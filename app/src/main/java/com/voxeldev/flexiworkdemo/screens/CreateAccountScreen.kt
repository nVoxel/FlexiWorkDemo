package com.voxeldev.flexiworkdemo.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.voxeldev.flexiworkdemo.components.ClickableOutlinedTextField
import com.voxeldev.flexiworkdemo.components.JoinButton
import com.voxeldev.flexiworkdemo.components.PreviewBase
import com.voxeldev.flexiworkdemo.navigation.LocalNavController
import com.voxeldev.flexiworkdemo.navigation.NavigationScreen

/**
 * @author nvoxel
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen() {
    val navController = LocalNavController.current

    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(state = rememberTopAppBarState())

    var nameText by rememberSaveable { mutableStateOf("") }

    val cities = rememberSaveable { listOf("Казань", "Москва", "Санкт-Петербург", "Екатеринбург") }
    var selectedCity by rememberSaveable { mutableStateOf(cities[0]) }
    var cityListVisible by rememberSaveable { mutableStateOf(false) }

    var emailText by rememberSaveable { mutableStateOf("") }

    var passwordText by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = "FlexiWork",
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
                .verticalScroll(state = scrollState),
        ) {
            Text(
                text = "Регистрация",
                style = MaterialTheme.typography.titleLarge,
            )

            Spacer(modifier = Modifier.height(height = 16.dp))

            Text(
                text = "Имя",
                fontWeight = FontWeight.ExtraBold,
            )

            Spacer(modifier = Modifier.height(height = 16.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = nameText,
                onValueChange = { nameText = it },
                placeholder = { Text(text = "Ваше имя") },
                shape = RoundedCornerShape(size = 32.dp),
            )

            Spacer(modifier = Modifier.height(height = 32.dp))

            Text(
                text = "Выберите ваш город",
                fontWeight = FontWeight.ExtraBold,
            )

            Spacer(modifier = Modifier.height(height = 16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                ClickableOutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = selectedCity,
                    onClick = { cityListVisible = true },
                )

                CityList(
                    cities = cities,
                    selectedCity = selectedCity,
                    isVisible = cityListVisible,
                    onSelect = {
                        selectedCity = it
                        cityListVisible = false
                    },
                    onDismiss = { cityListVisible = false },
                )
            }

            Spacer(modifier = Modifier.height(height = 32.dp))

            Text(
                text = "Почта",
                fontWeight = FontWeight.ExtraBold,
            )

            Spacer(modifier = Modifier.height(height = 16.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = emailText,
                onValueChange = { emailText = it },
                placeholder = { Text(text = "Email@email.com") },
                shape = RoundedCornerShape(size = 32.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )

            Spacer(modifier = Modifier.height(height = 32.dp))

            Text(
                text = "Пароль",
                fontWeight = FontWeight.ExtraBold,
            )

            Spacer(modifier = Modifier.height(height = 16.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = passwordText,
                onValueChange = { if (!it.contains("\n")) passwordText = it },
                placeholder = { Text(text = "*********") },
                shape = RoundedCornerShape(size = 32.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.VisibilityOff
                    else Icons.Filled.Visibility

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, "Видимость пароля")
                    }
                }
            )

            Spacer(modifier = Modifier.height(height = 16.dp))

            JoinButton(
                onClick = {
                    navController.navigate(NavigationScreen.Home.routeWithArguments) {
                        popUpTo(NavigationScreen.Welcome.routeWithArguments) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun CityList(
    cities: List<String>,
    selectedCity: String,
    isVisible: Boolean,
    onSelect: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    DropdownMenu(
        expanded = isVisible,
        onDismissRequest = onDismiss,
    ) {
        cities.forEach { city ->
            DropdownMenuItem(
                text = { Text(text = city) },
                onClick = { onSelect(city) },
                trailingIcon = {
                    if (city == selectedCity) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = "Выбран")
                    }
                }
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun CreateAccountScreenPreviewLight() {
    PreviewBase {
        CreateAccountScreen()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CreateAccountScreenPreviewDark() {
    PreviewBase {
        CreateAccountScreen()
    }
}
