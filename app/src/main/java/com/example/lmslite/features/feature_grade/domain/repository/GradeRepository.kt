package com.example.lmslite.features.feature_grade.domain.repository

import com.example.lmslite.core.common.Resource
import com.example.lmslite.features.feature_grade.domain.model.Grade
import kotlinx.coroutines.flow.Flow

interface GradeRepository {
    fun getAllGrades(): Flow<Resource<List<Grade>>>
}