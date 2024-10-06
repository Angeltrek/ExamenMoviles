package com.feature.examenmoviles.framework.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private fun myFunction() {
        viewModelScope.launch(Dispatchers.IO) {
        }
    }
}