package com.voxeldev.flexiworkdemo.navigation.sharedviewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.voxeldev.flexiworkdemo.models.Payment
import com.voxeldev.flexiworkdemo.models.dateTime
import com.voxeldev.flexiworkdemo.models.paymentTypes
import com.voxeldev.flexiworkdemo.models.proofType
import com.voxeldev.flexiworkdemo.models.random
import java.util.UUID

class ReservationsViewModel: ViewModel() {

    private val _booked: MutableLiveData<List<Payment>> = MutableLiveData(listOf())
    val booked: LiveData<List<Payment>> = _booked

    private var baseId = 0

    fun addBooked(id: Int) {
        val paymentType = paymentTypes[random.nextInt(paymentTypes.size)]
        val proof = proofType[random.nextInt(proofType.size)]
        val newList = mutableListOf(
            Payment(
                paymentId = baseId,
                coworkingId = id,
                proof = proof,
                dateTime = dateTime,
                paymentType = paymentType

            )
        )
        baseId += 1
        _booked.value?.let { newList.addAll(it) }
        _booked.value = newList
    }

    fun addBooked(payment: Payment) {
        val newList = mutableListOf(payment)
        _booked.value?.let { newList.addAll(it) }
        _booked.value = newList
    }


    fun removeBooked(id: Int) {
        val newList = mutableListOf<Payment>()
        _booked.value?.let { booked -> newList.addAll(booked.filter { it.paymentId != id }) }
        _booked.value = newList
    }
}