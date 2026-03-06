package com.example.lmslite.app.di

import android.app.Application
import androidx.room.Room
import com.example.lmslite.core.Database.AppDatabase
import com.example.lmslite.features.feature_student.data.remote.StudentApi
import com.example.lmslite.features.feature_student.data.repository.StudentRepositoryImpl
import com.example.lmslite.features.feature_student.domain.repository.StudentRepository
import com.example.lmslite.features.features_subject.data.remote.SubjectApi
import com.example.lmslite.features.features_subject.data.repository.SubjectRepositoryImpl
import com.example.lmslite.features.features_subject.domain.repository.SubjectRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //các đối tượng được tạo sẽ không mất khi app vẫn chạy
object AppModule {
    @Provides
    @Singleton //đảm bảo tạo đối tượng duy nhất 1 lần
    fun provideDatabase(app: Application): AppDatabase{ //mượn quyền application để tạo bảng trên điện thoại
        return Room.databaseBuilder( //khởi tạo file cơ sở dữ liệu vật lí trên điện thoại
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build() // tự động xoá bản cũ khi thêm/xoá cột và tạo bản mới tránh crash
    }
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{ //gọi API từ Server
        return Retrofit.Builder()
            .baseUrl("https://get/") // đến url
            .addConverterFactory(MoshiConverterFactory.create()) //MoshiConverterFactory dùng để đọc json
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
    fun provideStudentRepository(db: AppDatabase, api: StudentApi): StudentRepository{
        return StudentRepositoryImpl(api, db.studentDao)
    }
    @Provides
    @Singleton
    fun provideSubjectRepository(db: AppDatabase, api: SubjectApi): SubjectRepository{
        return SubjectRepositoryImpl(api, db.subjectDao)
    }
}