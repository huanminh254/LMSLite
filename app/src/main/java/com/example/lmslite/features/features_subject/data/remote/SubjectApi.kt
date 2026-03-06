package com.example.lmslite.features.features_subject.data.remote

import com.example.lmslite.features.features_subject.data.remote.dto.SubjectDto
import retrofit2.http.GET
import retrofit2.http.Path

interface SubjectApi {
    @GET("subjects")
    suspend fun getSubjects(): List<SubjectDto>
    @GET("grades/{id}")
    suspend fun getSubjectId(
        @Path("id") id: Int
    ): SubjectDto
}