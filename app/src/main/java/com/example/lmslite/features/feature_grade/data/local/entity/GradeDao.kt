package com.example.lmslite.features.feature_grade.data.local.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lmslite.features.feature_student.data.local.entity.StudentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GradeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrade(grades: List<GradeEntity>)
    @Query("SELECT * FROM gradeentity")
    fun getAllGrades(): Flow<List<GradeEntity>>
    @Delete
    suspend fun deleteGrade(grade: GradeEntity)
    @Query("DELETE FROM gradeentity")
    suspend fun clearAllGrades()
}