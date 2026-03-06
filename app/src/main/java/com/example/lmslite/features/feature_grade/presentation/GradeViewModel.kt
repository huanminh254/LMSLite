package com.example.lmslite.features.feature_grade.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lmslite.core.common.Resource
import com.example.lmslite.features.feature_grade.domain.model.Grade
import com.example.lmslite.features.feature_grade.domain.repository.GradeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GradeViewModel @Inject constructor(
    private val repo: GradeRepository
): ViewModel() {
    private val _state = MutableStateFlow(GradeState())
    val state = _state.asStateFlow()
    init {
        getGrades()
    }
    private fun getGrades(){
        repo.getAllGrades().onEach { result->
            _state.value = when(result){
                is Resource.Error -> {
                    _state.value.copy(isLoading = false, error = result.message)
                }
                is Resource.Success-> {
                    _state.value.copy(isLoading = false, grades = result.data ?: emptyList())
                }
                is Resource.Loading-> {
                    _state.value.copy(isLoading = true, error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}