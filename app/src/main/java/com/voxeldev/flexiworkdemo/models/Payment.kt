package com.voxeldev.flexiworkdemo.models

data class Payment(
    val paymentId: Int,
    val coworkingId: Int,
    val proof: Boolean,
    val dateTime: String,
    val paymentType: String,
)