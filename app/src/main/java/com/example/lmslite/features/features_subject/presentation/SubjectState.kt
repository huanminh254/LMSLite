package com.example.lmslite.features.features_subject.presentation

import com.example.lmslite.features.feature_student.domain.model.Student
import com.example.lmslite.features.features_subject.domain.model.Subject

data class SubjectState (
    val subjects: List<Subject> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)