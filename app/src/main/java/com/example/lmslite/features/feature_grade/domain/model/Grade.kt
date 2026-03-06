package com.example.lmslite.features.feature_grade.domain.model

import com.squareup.moshi.Json

class Grade(
    val id: Int = 0,
    val idSubject: Int,
    val idStudent: Int,
    val process: Float? = null,
    val final: Float? = null
)