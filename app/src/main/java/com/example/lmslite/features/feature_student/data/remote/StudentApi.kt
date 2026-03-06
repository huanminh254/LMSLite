package com.example.lmslite.features.feature_student.data.remote

import com.example.lmslite.features.feature_student.data.remote.dto.StudentDto
import retrofit2.http.GET
import retrofit2.http.Path

interface StudentApi {
    @GET("students")
    suspend fun getStudents(): List<StudentDto>
    @GET("students/{id}")
    suspend fun getStudentId(
        @Path("id") id: Int
    ): StudentDto

}