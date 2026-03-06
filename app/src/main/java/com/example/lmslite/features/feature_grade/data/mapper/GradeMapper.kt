package com.example.lmslite.features.feature_grade.data.mapper

import com.example.lmslite.features.feature_grade.data.local.entity.GradleEntity
import com.example.lmslite.features.feature_grade.domain.model.Grade
import com.example.lmslite.features.feature_grade.remote.dto.GradeDto

class GradeMapper {
    //Map từ Json -> Model
    fun GradeDto.toGrade(): Grade{
        return Grade(
            id = id,
            idSubject = idSubject,
            idStudent = idStudent,
            process = process,
            final = final
        )
    }
    //Map từ Model -> Entity
    fun Grade.toGradeEntity(): GradleEntity{
        return GradleEntity(
            id = id,
            idSubject = idSubject,
            idStudent = idStudent,
            process = process,
            final = final
        )
    }
    //Map từ Entity->Json
    fun GradleEntity.toGrade(): GradeDto{
        return GradeDto(
            id = id,
            idSubject = idSubject,
            idStudent = idStudent,
            process = process,
            final = final
        )
    }
}