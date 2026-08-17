package com.example.note_taking.notes.data.repository

import com.example.note_taking.notes.data.dto.CreateNoteRequestDto
import com.example.note_taking.notes.data.mappers.toNote
import com.example.note_taking.notes.data.network.NoteApi
import com.example.note_taking.notes.domain.Note
import com.example.note_taking.notes.domain.NoteRepository

class NoteRepositoryImpl(
    private val noteApi: NoteApi
): NoteRepository {
    override suspend fun getNotes():List<Note> {
        return noteApi.getNotes().map {
            it.toNote()
        }
    }

    override suspend fun createNote(
        title: String,
        content: String
    ): Note {
        val createdNote = noteApi.createNote(
            CreateNoteRequestDto(
                title = title,
                content = content
            )
        )
        return createdNote.toNote()
    }
}