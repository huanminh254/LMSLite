package com.example.lmslite.features.features_subject.domain.repository

import com.example.lmslite.core.common.Resource
import com.example.lmslite.features.feature_student.domain.model.Student
import com.example.lmslite.features.features_subject.domain.model.Subject
import kotlinx.coroutines.flow.Flow

interface SubjectRepository {
    suspend fun getAllSubjects(): Flow<Resource<List<Subject>>>
}