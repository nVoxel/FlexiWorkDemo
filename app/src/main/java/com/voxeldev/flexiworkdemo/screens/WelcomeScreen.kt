package com.voxeldev.flexiworkdemo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voxeldev.flexiworkdemo.R
import com.voxeldev.flexiworkdemo.components.JoinButton
import com.voxeldev.flexiworkdemo.components.PreviewBase
import com.voxeldev.flexiworkdemo.navigation.LocalNavController
import com.voxeldev.flexiworkdemo.navigation.NavigationScreen

/**
 * @author nvoxel
 */
@Composable
fun WelcomeScreen() {
    val navController = LocalNavController.current

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 64.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Image(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(size = 8.dp)),
            painter = painterResource(id = R.drawable.welcome),
            contentDescription = "FlexiWork",
        )

        Spacer(modifier = Modifier.height(height = 32.dp))

        Text(
            text = "FlexiWork",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(height = 16.dp))

        Text(
            text = "Найдите ваш идеальный коворкинг",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(height = 32.dp))

        JoinButton(onClick = { navController.navigate(NavigationScreen.CreateAccount.routeWithArguments) })
    }
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    PreviewBase {
        WelcomeScreen()
    }
}
