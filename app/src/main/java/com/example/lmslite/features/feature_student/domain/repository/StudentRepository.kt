package com.example.lmslite.features.feature_student.domain.repository

import com.example.lmslite.core.common.Resource
import com.example.lmslite.features.feature_student.domain.model.Student
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    suspend fun getAllStudents(): Flow<Resource<List<Student>>>
}