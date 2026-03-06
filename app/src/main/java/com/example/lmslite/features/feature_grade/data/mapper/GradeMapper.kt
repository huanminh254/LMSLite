package com.example.lmslite.features.feature_grade.data.mapper

import com.example.lmslite.features.feature_grade.data.local.entity.GradeEntity
import com.example.lmslite.features.feature_grade.domain.model.Grade
import com.example.lmslite.features.feature_grade.remote.dto.GradeDto

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
    fun Grade.toGradeEntity(): GradeEntity{
        return GradeEntity(
            id = id,
            idSubject = idSubject,
            idStudent = idStudent,
            process = process,
            final = final
        )
    }
    //Map từ Entity->Json
    fun GradeEntity.toGrade(): Grade{
        return Grade(
            id = id,
            idSubject = idSubject,
            idStudent = idStudent,
            process = process,
            final = final
        )
    }