package com.example.lmslite.features.feature_grade.data.local.entity

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lmslite.features.feature_student.data.local.entity.StudentEntity
import kotlinx.coroutines.flow.Flow

interface GradeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrade(grades: List<GradleEntity>)
    @Query("SELECT * FROM gradleentity")
    fun getAllGrades(): Flow<List<GradleEntity>>
    @Delete
    suspend fun deleteGrade(grade: GradleEntity)
    @Query("DELETE FROM gradleentity")
    suspend fun clearAllGrades()
}