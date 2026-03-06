package com.example.lmslite.features.feature_student.data.repository

import com.example.lmslite.core.common.Resource
import com.example.lmslite.features.feature_student.data.local.entity.StudentDao
import com.example.lmslite.features.feature_student.data.mapper.toEntity
import com.example.lmslite.features.feature_student.data.mapper.toStudent
import com.example.lmslite.features.feature_student.data.remote.StudentApi
import com.example.lmslite.features.feature_student.data.remote.dto.StudentDto
import com.example.lmslite.features.feature_student.domain.model.Student
import com.example.lmslite.features.feature_student.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StudentRepositoryImpl(
    private val dbApi: StudentApi,
    private val dbRoom: StudentDao
): StudentRepository {

    override fun getAllStudents(): Flow<Resource<List<Student>>> = flow {
        // 1. Báo cho UI hiện vòng xoay Loading
        emit(Resource.Loading())

        // 2. Giả lập delay mạng cho giống thật
        kotlinx.coroutines.delay(1000)

        // 3. Tạo danh sách sinh viên giả (Mock Data)
        val mockStudents = listOf(
            Student(id = 1, name = "Nguyễn Văn Huân", studentCode = "TLU001", email = "huan@tlu.edu.vn"),
            Student(id = 2, name = "Linh Xinh", studentCode = "TLU002", email = "linh@tlu.edu.vn"),
            Student(id = 3, name = "Trần Trung Thực", studentCode = "TLU003", email = "thuc@tlu.edu.vn")
        )

        // 4. Báo cho UI là đã lấy dữ liệu thành công!
        emit(Resource.Success(mockStudents))

        // Tạm thời comment code thật lại để bao giờ có Server thì dùng sau
        /*
        try {
            val resultApi = dbApi.getStudents()
            // ... logic xử lý api ...
        } catch (e: Exception) {
            emit(Resource.Error("Lỗi: ${e.message}"))
        }
        */
    }
}