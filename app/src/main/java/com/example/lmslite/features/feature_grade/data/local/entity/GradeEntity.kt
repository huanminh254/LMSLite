package com.example.lmslite.features.feature_grade.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.lmslite.features.feature_student.data.local.entity.StudentEntity
import com.example.lmslite.features.features_subject.data.local.entity.SubjectEntity

@Entity(
    tableName = "grades",
    foreignKeys = [
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = ["id"],
            childColumns = ["idStudent"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SubjectEntity::class,
            parentColumns = ["id"],
            childColumns = ["idSubject"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GradeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idSubject: Int,
    val idStudent: Int,
    val process: Float? = null,
    val final: Float? = null
)