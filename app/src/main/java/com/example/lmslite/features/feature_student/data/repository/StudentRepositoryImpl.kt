package com.example.lmslite.features.feature_student.data.repository

import com.example.lmslite.core.common.Resource
import com.example.lmslite.features.feature_student.data.local.entity.StudentDao
import com.example.lmslite.features.feature_student.data.mapper.toEntity
import com.example.lmslite.features.feature_student.data.mapper.toStudent
import com.example.lmslite.features.feature_student.data.remote.StudentApi
import com.example.lmslite.features.feature_student.data.remote.dto.StudentDto
import com.example.lmslite.features.feature_student.domain.model.Student
import com.example.lmslite.features.feature_student.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StudentRepositoryImpl(
    private val dbApi: StudentApi, private val dbRoom: StudentDao
): StudentRepository {
    override suspend fun getAllStudents(): Flow<Resource<List<Student>>> = flow {
        emit(Resource.Loading())
        try {
            val resultApi: List<StudentDto> = dbApi.getStudents()
            val dbModel: List<Student> = resultApi.map { it.toStudent() }
            val dbEntity = dbModel.map { it.toEntity() }
            dbRoom.clearAllStudents()
            dbRoom.insertStudents(dbEntity)
            emit(Resource.Success(dbModel))
        }catch (e: Exception){
            emit(Resource.Error(message = "Loi ket noi ${e.message}"))
        }
    }
}