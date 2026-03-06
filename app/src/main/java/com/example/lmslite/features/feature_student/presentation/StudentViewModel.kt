package com.example.lmslite.features.feature_student.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lmslite.core.common.Resource
import com.example.lmslite.features.feature_student.data.local.entity.StudentDao
import com.example.lmslite.features.feature_student.domain.model.Student
import com.example.lmslite.features.feature_student.domain.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class StudentViewModel @Inject constructor(
    private val repo: StudentRepository
): ViewModel() {
    private val _state = MutableStateFlow(StudentState())
    val state = _state.asStateFlow() // UI chỉ được đọc data mà không động đến được
    init {
        getStudent()
    }
    private fun getStudent(){
        repo.getAllStudents().onEach { result->
            _state.value = when(result){
                is Resource.Loading->{
                    _state.value.copy(isLoading = true, error = null)
                }
                is Resource.Success->{
                    _state.value.copy(isLoading = false, students = result.data ?: emptyList())
                }
                is Resource.Error->{
                    _state.value.copy(isLoading = false, error = result.message)
                }
            }
        }.launchIn(viewModelScope) //tự động dừng cập nhật đến UI
    }
}