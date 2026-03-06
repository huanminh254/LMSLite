package com.example.lmslite.features.feature_student.data.local.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface StudentDao {
    @Query("SELECT * FROM studententity")
    fun getAllStudents(): Flow<List<StudentEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudents(students: List<StudentEntity>)
    @Delete
    suspend fun deleteStudent(student: StudentEntity)
    @Query("DELETE FROM StudentEntity")
    suspend fun clearAllStudents()
}