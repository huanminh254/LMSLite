package com.example.lmslite.features.feature_grade.remote.dto

import com.squareup.moshi.Json


data class GradeDto(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "id_subject") val idSubject: Int,
    @field:Json(name = "id_student")val idStudent: Int,
    @field:Json(name = "process")val process: Float? = null,
    @field:Json(name = "final")val final: Float? = null
)