package com.example.lmslite.features.feature_student.data.mapper

import com.example.lmslite.features.feature_student.data.local.entity.StudentEntity
import com.example.lmslite.features.feature_student.data.remote.dto.StudentDto
import com.example.lmslite.features.feature_student.domain.model.Student

//Map từ API về model
fun StudentDto.toStudent(): Student{
    return Student(
        id = id?: 0,
        name = fullName?: "Unknown",
        studentCode = code ?: "Unknown",
        email = contactEmail ?: "Unknown"
    )
}
//Map từ Model sang Entity
fun Student.toEntity(): StudentEntity{
    return StudentEntity(
        id = id,
        name = name,
        studentCode = studentCode,
        email = email
    )
}
//Map từ Entity sang domain

fun StudentEntity.toStudent(): Student{
    return Student(
        id = id,
        name = name,
        studentCode = studentCode,
        email = email
    )
}