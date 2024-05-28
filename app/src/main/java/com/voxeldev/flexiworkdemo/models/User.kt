package com.voxeldev.flexiworkdemo.models

data class User(
    val userId: Int,
    val username: String,
    val email: String,
    val password: String,
    val addressId: String,
    val street: String,
    val city: String,
)