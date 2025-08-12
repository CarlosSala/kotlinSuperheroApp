package com.example.superheroapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroapp.data.network.model.SuperheroDetailResponseDto
import com.example.superheroapp.domain.GetSuperheroDetailUseCase
import com.example.superheroapp.ui.model.SuperheroDetailUI
import com.example.superheroapp.ui.model.toUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SuperheroDetailViewModel : ViewModel() {

    private val getSuperheroDetailUseCase = GetSuperheroDetailUseCase()

    private val _superheroDetailModel = MutableStateFlow<SuperheroDetailUI?>(null)
    val superheroDetailModel: MutableStateFlow<SuperheroDetailUI?> = _superheroDetailModel

    private val _progressBar = MutableStateFlow(false)
    val progressBar: MutableStateFlow<Boolean> = _progressBar

    fun getSuperheroDetail(superheroId: String) {

        viewModelScope.launch {
            _progressBar.value = true
            val result = getSuperheroDetailUseCase(superheroId)
            _superheroDetailModel.value = result.toUIModel()
            _progressBar.value = false
        }
    }
}