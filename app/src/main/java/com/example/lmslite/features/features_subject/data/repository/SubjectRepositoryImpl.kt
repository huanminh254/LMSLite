package com.example.lmslite.features.features_subject.data.repository

import com.example.lmslite.core.common.Resource
import com.example.lmslite.features.features_subject.data.local.entity.SubjectDao
import com.example.lmslite.features.features_subject.data.mapper.toSubject
import com.example.lmslite.features.features_subject.data.mapper.toSubjectEntity
import com.example.lmslite.features.features_subject.data.remote.SubjectApi
import com.example.lmslite.features.features_subject.data.remote.dto.SubjectDto
import com.example.lmslite.features.features_subject.domain.model.Subject
import com.example.lmslite.features.features_subject.domain.repository.SubjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SubjectRepositoryImpl(
    private val dbApi: SubjectApi, private val dbRoom: SubjectDao
): SubjectRepository {
    override suspend fun getAllSubjects(): Flow<Resource<List<Subject>>> = flow {
        emit(Resource.Loading())
        try{
            val resultApi: List<SubjectDto> = dbApi.getSubjects()
            val dbModel: List<Subject> = resultApi.map { it.toSubject() }
            val dbEntity = dbModel.map { it.toSubjectEntity() }
            dbRoom.clearAllSubject()
            dbRoom.insertSubject(dbEntity)
            emit(Resource.Success(dbModel))
        }catch (e: Exception){
            emit(Resource.Error(message = "Loi ket noi ${e.message}"))
        }
    }

}