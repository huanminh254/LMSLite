package com.example.lmslite.features.feature_student.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StudentEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val studentCode: String,
    val email: String
)