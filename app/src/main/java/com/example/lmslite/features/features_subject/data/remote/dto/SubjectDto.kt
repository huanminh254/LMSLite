package com.example.lmslite.features.features_subject.data.remote.dto

import com.squareup.moshi.Json


data class SubjectDto(
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "name_subject") val nameSubject: String,
)