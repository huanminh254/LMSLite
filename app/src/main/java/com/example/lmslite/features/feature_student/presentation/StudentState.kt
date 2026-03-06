package com.example.lmslite.features.feature_student.presentation

import com.example.lmslite.features.feature_student.domain.model.Student

data class StudentState (
    val students: List<Student> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)