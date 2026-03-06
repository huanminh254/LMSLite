package com.example.lmslite.features.feature_grade.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "grades",
    foreignKeys = [
        ForeignKey(
            entity = GradleEntity::class,
            parentColumns = ["id"],
            childColumns = ["idStudent"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity =
        )
    ]
)
data class GradleEntity (
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val idSubject: Int?,
    val idStudent: Int?,
    val process: Float? = null,
    val final: Float? = null
)