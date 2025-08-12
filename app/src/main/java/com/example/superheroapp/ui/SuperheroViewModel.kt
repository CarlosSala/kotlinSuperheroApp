package com.example.superheroapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroapp.domain.SearchSuperheroUseCase
import com.example.superheroapp.ui.model.SuperherosUI
import com.example.superheroapp.ui.model.toUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SuperheroViewModel : ViewModel() {

    private val searchSuperheroUseCase = SearchSuperheroUseCase()

    private val _superheroModel = MutableStateFlow<SuperherosUI?>(null)
    val superheroModel: MutableStateFlow<SuperherosUI?> = _superheroModel

    private val _progressBar = MutableStateFlow(false)
    val progressBar: MutableStateFlow<Boolean> = _progressBar

    fun searchSuperhero(superhero: String) {

        viewModelScope.launch {
            _progressBar.value = true
            val result = searchSuperheroUseCase(superhero)
            _superheroModel.value = result.toUIModel()
            _progressBar.value = false
        }
    }
}