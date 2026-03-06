package com.example.lmslite.features.feature_grade.presentation

import com.example.lmslite.features.feature_grade.domain.model.Grade

data class GradeState (
    val grades: List<Grade> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)