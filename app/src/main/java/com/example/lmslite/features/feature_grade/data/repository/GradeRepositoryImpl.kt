package com.example.lmslite.features.feature_grade.data.repository

import com.example.lmslite.core.common.Resource
import com.example.lmslite.features.feature_grade.data.local.entity.GradeDao
import com.example.lmslite.features.feature_grade.data.mapper.toGrade
import com.example.lmslite.features.feature_grade.data.mapper.toGradeEntity
import com.example.lmslite.features.feature_grade.domain.model.Grade
import com.example.lmslite.features.feature_grade.domain.repository.GradeRepository
import com.example.lmslite.features.feature_grade.remote.GradeApi
import com.example.lmslite.features.feature_grade.remote.dto.GradeDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GradeRepositoryImpl (
    private val dbApi: GradeApi,private val dbRoom: GradeDao
): GradeRepository {
    override fun getAllGrades(): Flow<Resource<List<Grade>>> = flow {
        emit(Resource.Loading())
        try {
            val resultApi: List<GradeDto> = dbApi.getGrades()
            val dbModel: List<Grade> = resultApi.map { it.toGrade() }
            val dbEntity = dbModel.map { it.toGradeEntity() }
            emit(Resource.Success(dbModel))
        }catch (e: Exception){
            emit(Resource.Error(message = "Loi ket noi ${e.message}"))
        }
    }

}