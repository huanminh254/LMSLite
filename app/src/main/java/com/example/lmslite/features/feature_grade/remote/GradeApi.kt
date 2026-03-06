package com.example.lmslite.features.feature_grade.remote

import com.example.lmslite.features.feature_grade.remote.dto.GradeDto
import com.example.lmslite.features.feature_student.data.remote.dto.StudentDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GradeApi {
    @GET("grades")
    suspend fun getGrades(): List<GradeDto>
    @GET("grades/{id}")
    suspend fun getGradeId(
        @Path("id") id: Int
    ): GradeDto

}