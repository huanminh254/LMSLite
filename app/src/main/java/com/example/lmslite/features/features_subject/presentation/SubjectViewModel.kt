package com.example.lmslite.features.features_subject.presentation

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lmslite.core.common.Resource
import com.example.lmslite.features.features_subject.domain.model.Subject
import com.example.lmslite.features.features_subject.domain.repository.SubjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SubjectViewModel @Inject constructor(
    private val repo: SubjectRepository
): ViewModel() {
    private val _state = MutableStateFlow(SubjectState())
    val state = _state.asStateFlow()
    init {
        getSubject()
    }
    private fun getSubject(){
        repo.getAllSubjects().onEach { result->
            _state.value = when(result){
                is Resource.Error-> {
                    _state.value.copy(isLoading = false, error = result.message)
                }
                is Resource.Loading -> {
                    _state.value.copy(isLoading = true, error = null)
                }
                is Resource.Success -> {
                    _state.value.copy(isLoading = false, subjects = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}