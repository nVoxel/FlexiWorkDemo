package com.voxeldev.flexiworkdemo.navigation.sharedviewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author nvoxel
 */
class FavoritesViewModel : ViewModel() {

    private val _favorites: MutableLiveData<List<Int>> = MutableLiveData(listOf())
    val favorites: LiveData<List<Int>> = _favorites

    fun addFavorite(id: Int) {
        val newList = mutableListOf(id)
        _favorites.value?.let { newList.addAll(it) }
        _favorites.value = newList
    }

    fun removeFavorite(id: Int) {
        val newList = mutableListOf<Int>()
        _favorites.value?.let { favorites -> newList.addAll(favorites.filter { it != id }) }
        _favorites.value = newList
    }
}
