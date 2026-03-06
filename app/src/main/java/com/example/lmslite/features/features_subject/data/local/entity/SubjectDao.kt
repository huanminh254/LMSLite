package com.example.lmslite.features.features_subject.data.local.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface SubjectDao {
    @Query("SELECT * FROM subjectentity")
    fun getAllSubjects(): Flow<List<SubjectEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subjects: List<SubjectEntity>)
    @Delete
    suspend fun deleteSubject(subject: SubjectEntity)
    @Query("DELETE FROM subjectentity")
    suspend fun clearAllSubject()
}