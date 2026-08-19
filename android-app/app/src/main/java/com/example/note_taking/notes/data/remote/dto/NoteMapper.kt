package com.example.note_taking.notes.data.remote.dto

import com.example.note_taking.notes.domain.Note

fun NoteDto.toNote(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt,
        isFavorite = isFavorite,
    )
}
