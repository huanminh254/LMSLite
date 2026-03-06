package com.example.lmslite.core.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lmslite.features.feature_grade.data.local.entity.GradeDao
import com.example.lmslite.features.feature_grade.data.local.entity.GradeEntity
import com.example.lmslite.features.feature_student.data.local.entity.StudentDao
import com.example.lmslite.features.feature_student.data.local.entity.StudentEntity
import com.example.lmslite.features.features_subject.data.local.entity.SubjectDao
import com.example.lmslite.features.features_subject.data.local.entity.SubjectEntity


@Database(
    entities = [
        StudentEntity::class, //bảng dữ liệu chính
        SubjectEntity::class,
        GradeEntity::class
    ],
    version =  1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract val studentDao: StudentDao
    abstract val subjectDao: SubjectDao
    abstract val gradeDao: GradeDao
    companion object{
        const val DATABASE_NAME = "lms_lite_db"
    }
}