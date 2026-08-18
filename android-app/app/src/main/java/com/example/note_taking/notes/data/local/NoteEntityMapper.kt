package com.example.note_taking.notes.data.local

import com.example.note_taking.notes.domain.Note
import kotlin.time.Instant

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        isFavorite = isFavorite,
        createdAt = Instant.fromEpochMilliseconds(createdAt)
    )
}

fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content,
        isFavorite = isFavorite,
        createdAt = createdAt.toEpochMilliseconds()
    )
}