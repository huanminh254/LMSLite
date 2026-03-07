package com.example.lmslite.features.feature_student.data.remote

import com.example.lmslite.features.feature_student.data.remote.dto.StudentDto
import retrofit2.http.GET
import retrofit2.http.Path

interface StudentApi {
    @GET("student") //Url lấy db từ api
    suspend fun getStudents(): List<StudentDto>
    @GET("student/{id}")
    suspend fun getStudentId(
        @Path("id") id: String
    ): StudentDto //Trả về 1 object JSON

}