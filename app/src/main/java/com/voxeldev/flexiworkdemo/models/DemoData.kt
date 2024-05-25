package com.voxeldev.flexiworkdemo.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalPizza
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material.icons.filled.WavingHand
import com.voxeldev.flexiworkdemo.R

val categoryList = listOf(
    Category(
        id = 0,
        title = "Открытые",
        icon = Icons.Default.WavingHand,
    ),
    Category(
        id = 1,
        title = "Для встреч",
        icon = Icons.Default.MeetingRoom,
    ),
    Category(
        id = 2,
        title = "Приватные",
        icon = Icons.Default.Lock,
    ),
    Category(
        id = 3,
        title = "Отдых",
        icon = Icons.Default.LocalPizza,
    ),
)

val coworkingList = listOf(
    Coworking(
        id = 0,
        name = "SOK Пушкин (Пушкина)",
        distanceMeters = 1000,
        rating = 4.8f,
        workspacesCount = 20,
        computersCount = 15,
        pricePerHour = 300,
        image = R.drawable.coworking1,
    ),
    Coworking(
        id = 1,
        name = "Func (Островского)",
        distanceMeters = 1500,
        rating = 4.3f,
        workspacesCount = 35,
        computersCount = 20,
        pricePerHour = 250,
        image = R.drawable.coworking2,
    ),
    Coworking(
        id = 2,
        name = "EcoWorking (Ямашева)",
        distanceMeters = 800,
        rating = 4.9f,
        workspacesCount = 10,
        computersCount = 8,
        pricePerHour = 400,
        image = R.drawable.coworking3,
    ),
    Coworking(
        id = 3,
        name = "Параллель 34 (Профсоюзная)",
        distanceMeters = 2000,
        rating = 4.2f,
        workspacesCount = 40,
        computersCount = 25,
        pricePerHour = 280,
        image = R.drawable.coworking4,
    ),
    Coworking(
        id = 4,
        name = "Space Up (Космонавтов)",
        distanceMeters = 500,
        rating = 4.0f,
        workspacesCount = 15,
        computersCount = 12,
        pricePerHour = 350,
        image = R.drawable.coworking5,
    ),
    Coworking(
        id = 5,
        name = "Live (Красноармейская)",
        distanceMeters = 1200,
        rating = 4.7f,
        workspacesCount = 28,
        computersCount = 18,
        pricePerHour = 320,
        image = R.drawable.coworking6,
    ),
    Coworking(
        id = 6,
        name = "Коворкинг на Неве",
        distanceMeters = 2300,
        rating = 4.5f,
        workspacesCount = 50,
        computersCount = 30,
        pricePerHour = 230,
        image = R.drawable.coworking7,
    ),
    Coworking(
        id = 7,
        name = "Re: Work (Московское шоссе)",
        distanceMeters = 700,
        rating = 4.1f,
        workspacesCount = 8,
        computersCount = 5,
        pricePerHour = 450,
        image = R.drawable.coworking8,
    ),
    Coworking(
        id = 8,
        name = "CoworkyMe (Лиговский проспект)",
        distanceMeters = 1800,
        rating = 4.6f,
        workspacesCount = 30,
        computersCount = 20,
        pricePerHour = 380,
        image = R.drawable.coworking9,
    ),
    Coworking(
        id = 9,
        name = "Boiler (Щербаковская)",
        distanceMeters = 1100,
        rating = 5.0f,
        workspacesCount = 45,
        computersCount = 27,
        pricePerHour = 310,
        image = R.drawable.coworking10,
    )
)

val dateSelectList = listOf(
    DateSelect(
        0,
        "Выберите дату",
        "Сегодня"
    ),
    DateSelect(
        1,
        "Выберите время",
        "21:30"
    )
)
