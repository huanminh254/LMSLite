package com.example.lmslite.features.feature_student.data.remote.dto

import com.squareup.moshi.Json


data class StudentDto(
    @field:Json(name = "id") val id: String?,
    @field:Json(name = "studentCode") val studentCode: String?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "email") val email: String?
)