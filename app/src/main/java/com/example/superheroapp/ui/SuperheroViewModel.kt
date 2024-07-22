package com.example.superheroapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroapp.data.network.SuperheroDataResponse
import com.example.superheroapp.domain.SearchSuperheroUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SuperheroViewModel : ViewModel() {

    private val searchSuperheroUseCase = SearchSuperheroUseCase()

    private val _superheroModel = MutableStateFlow<SuperheroDataResponse?>(null)
    val superheroModel: MutableStateFlow<SuperheroDataResponse?> = _superheroModel

    private val _progressBar = MutableStateFlow(false)
    val progressBar: MutableStateFlow<Boolean> = _progressBar

    fun searchSuperhero(superhero: String) {
        viewModelScope.launch {
            _progressBar.value = true
            val result = searchSuperheroUseCase(superhero)
            _superheroModel.value = result
            _progressBar.value = false
        }
    }
}