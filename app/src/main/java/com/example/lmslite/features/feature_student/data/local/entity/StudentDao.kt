package com.example.lmslite.features.feature_student.data.local.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface StudentDao {
    @Query("SELECT * FROM StudentEntity")
    fun getAllStudents(): Flow<List<StudentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudents(students: List<StudentEntity>)

    @Query("DELETE FROM StudentEntity")
    suspend fun clearAllStudents()

    @Query("""
    SELECT * FROM StudentEntity 
    WHERE REPLACE(studentCode, ' ', '') LIKE '%' || REPLACE(:code, ' ', '') || '%'COLLATE NOCASE""")
    suspend fun searchStudentById(code: String): StudentEntity?
}