package com.example.lmslite.features.features_subject.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SubjectEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val nameSubject: String,
)