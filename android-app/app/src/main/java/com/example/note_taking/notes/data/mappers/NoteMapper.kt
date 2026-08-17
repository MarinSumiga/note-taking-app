package com.example.note_taking.notes.data.mappers

import com.example.note_taking.notes.data.dto.NoteDto
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

fun Note.toNoteDto(): NoteDto  {
    return NoteDto(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt,
        isFavorite = isFavorite,
    )
}