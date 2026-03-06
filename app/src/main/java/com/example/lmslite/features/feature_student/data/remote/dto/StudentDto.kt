package com.example.lmslite.features.feature_student.data.remote.dto

import com.squareup.moshi.Json


data class StudentDto(
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "full_name") val fullName: String?,
    @field:Json(name = "code")val code: String?,
    @field:Json(name = "contactEmail")val contactEmail: String?
)