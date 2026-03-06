package com.example.lmslite.features.features_subject.data.mapper

import com.example.lmslite.features.features_subject.data.local.entity.SubjectEntity
import com.example.lmslite.features.features_subject.data.remote.dto.SubjectDto
import com.example.lmslite.features.features_subject.domain.model.Subject

//Map từ API về model
fun SubjectDto.toSubject(): Subject{
    return Subject(
        id = id,
        nameSubject = nameSubject
    )
}
//Map từ Model sang Entity
fun Subject.toSubjectEntity(): SubjectEntity{
    return SubjectEntity(
        id = id,
        nameSubject = nameSubject
    )
}
//Map từ Entity sang domain

fun SubjectEntity.toSubject(): Subject{
    return Subject(
        id = id,
        nameSubject = nameSubject
    )
}