package com.example.lmslite.features.feature_student.data.repository

import androidx.compose.ui.geometry.Rect
import com.example.lmslite.core.common.Resource
import com.example.lmslite.features.feature_student.data.local.entity.StudentDao
import com.example.lmslite.features.feature_student.data.mapper.toEntity
import com.example.lmslite.features.feature_student.data.mapper.toStudent
import com.example.lmslite.features.feature_student.data.remote.StudentApi
import com.example.lmslite.features.feature_student.domain.model.Student
import com.example.lmslite.features.feature_student.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class StudentRepositoryImpl(
    private val api: StudentApi,
    private val dao: StudentDao
): StudentRepository {
//    val mockStudents = listOf(
//        Student(id = 1, name = "Nguyễn Minh Huấn", studentCode = "A49660", email = "huan@tlu.edu.vn"),
//        Student(id = 2, name = "Nguyễn Hoài Nam", studentCode = "A12345", email = "hnem@tlu.edu.vn"),
//        Student(id = 3, name = "Nguyễn Tùng Dương", studentCode = "A49633", email = "tduong@tlu.edu.vn")
//    )
    override fun getAllStudents(): Flow<Resource<List<Student>>> = flow {
        emit(Resource.Loading())
        val test = dao.getAllStudents().first() //
        if(test.isEmpty())
        {
            try{
                val studentsApi = api.getStudents()
                val studentModel = studentsApi.map{it.toStudent()}
                val studentEntity = studentModel.map { it.toEntity() }
                dao.clearAllStudents()
                dao.insertStudents(studentEntity)
            }catch (e: Exception){
                emit(Resource.Error("Không thể cập nhật từ server: ${e.message}"))
            }
        }
            dao.getAllStudents().collect { result->
                val students = result.map { it.toStudent() }
                emit(Resource.Success(students))
        }
    }


    override fun searchStudentById(code: String): Flow<Resource<Student?>> = flow {
        emit(Resource.Loading())
        try{
            val studentEntity = dao.searchStudentById(code.trim())
            if(studentEntity != null){
                emit(Resource.Success(studentEntity.toStudent()))
            }
            else{
                emit(Resource.Error("Không tìm thấy MSV $code"))
            }
//            val student = mockStudents.find { it.studentCode == code }
//            emit(Resource.Success(student))
        }catch (e: Exception){
            emit(Resource.Error("Lỗi Hệ Thống: ${e.localizedMessage}"))

        }
    }
}