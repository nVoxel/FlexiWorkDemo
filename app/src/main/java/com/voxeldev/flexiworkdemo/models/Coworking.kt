package com.voxeldev.flexiworkdemo.models

import androidx.annotation.DrawableRes

/**
 * @author nvoxel
 */
data class Coworking(
    val id: Int,
    val name: String,
    val distanceMeters: Int,
    val rating: Float, // max 5
    val workspacesCount: Int,
    val computersCount: Int,
    val pricePerHour: Int, // RUB
    @DrawableRes val image: Int,
)
