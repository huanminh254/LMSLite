package com.example.lmslite.core.network

import com.example.lmslite.features.feature_grade.remote.GradeApi
import com.example.lmslite.features.feature_student.data.remote.StudentApi
import com.example.lmslite.features.features_subject.data.remote.SubjectApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule
{
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory()) // Giúp Moshi hiểu Data Class của Kotlin
            .build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit{ //gọi API từ Server
        return Retrofit.Builder()
            .baseUrl("https://69abf53f9ca639a5217dd5d6.mockapi.io/api/get/") // đến url
            .addConverterFactory(MoshiConverterFactory.create(moshi)) //MoshiConverterFactory dùng để đọc json
            .build()
    }
    @Provides
    @Singleton
    fun provideStudentApi(retrofit: Retrofit): StudentApi = retrofit.create(StudentApi::class.java)
    @Provides
    @Singleton
    fun provideSubjectApi(retrofit: Retrofit): SubjectApi = retrofit.create(SubjectApi::class.java)
    @Provides
    @Singleton
    fun provideGradeApi(retrofit: Retrofit): GradeApi = retrofit.create(GradeApi::class.java)
}
