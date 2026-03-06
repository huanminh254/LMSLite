package com.example.lmslite.features.feature_grade.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.example.lmslite.features.feature_student.data.local.entity.StudentEntity
import com.example.lmslite.features.features_subject.data.local.entity.SubjectEntity

data class GradeWithSubject(
    @Embedded val grade: GradeEntity,
    @Relation(parentColumn = "studentId", entityColumn = "id")
    val student: StudentEntity,
    @Relation(parentColumn = "subjectId", entityColumn = "id")
    val subject: SubjectEntity
)