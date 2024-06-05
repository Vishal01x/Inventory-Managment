package com.exa.android.inventorymanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exa.android.inventorymanagement.Models.Category

class DataViewModel : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>(emptyList())

    val categories: LiveData<List<Category>>
        get() = _categories

    fun insertCategory(category: Category) {
        val currentList = _categories.value?.toMutableList() ?: mutableListOf()
        currentList.add(category)
        _categories.value = currentList.toList()
    }
}