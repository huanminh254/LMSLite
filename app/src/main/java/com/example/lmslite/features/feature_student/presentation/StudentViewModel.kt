package com.example.lmslite.features.feature_student.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lmslite.core.common.Resource
import com.example.lmslite.features.feature_student.domain.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.collections.emptyList


@HiltViewModel
class StudentViewModel @Inject constructor(
    private val repo: StudentRepository
): ViewModel() {
    private val _state = MutableStateFlow(StudentState())
    val state = _state.asStateFlow() // UI chỉ được đọc data mà không động đến được
//    init {
//        getStudent()
//    }
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
    fun onChanged(code: String){
        _state.value = _state.value.copy(searchCode = code)
    }
    fun checkSearch(){
        _state.value = _state.value.copy(isLoading = true, error = null)
        val code = _state.value.searchCode
        if(code.isBlank()){
            _state.value.copy(
                isLoading = false,
                error = "Lỗi Nhập Dữ Liệu"
            )
            return
        }
        _state.value = _state.value.copy(isLoading = false, error = null)
        repo.searchStudentById(code).onEach { event->
            _state.value = when(event){
                is Resource.Success-> {
                    val student = event.data?.let { listOf(event.data) ?: emptyList() }
                    _state.value.copy(isLoading = false, students = student ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value.copy(isLoading = true, error = null)
                }
                is Resource.Error -> {
                    _state.value.copy(isLoading = false, error = event.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}