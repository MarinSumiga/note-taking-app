package com.example.note_taking.notes.data.repository

import com.example.note_taking.notes.data.local.NoteDao
import com.example.note_taking.notes.data.local.toEntity
import com.example.note_taking.notes.data.local.toNote
import com.example.note_taking.notes.domain.Note
import com.example.note_taking.notes.domain.NoteRepository


class NoteRepositoryImpl(
    private val noteDao: NoteDao
): NoteRepository {
    override suspend fun getNotes():List<Note> {
        return noteDao.getNotes().map {
            it.toNote()
        }
    }
    override suspend fun upsertNote(note: Note) {
        noteDao.upsertNote(note.toEntity()
        )
    }

    override suspend fun toggleFavorite(id: String): Note {
        val existingNote = findNoteById(id)
        val updatedNote = existingNote.copy(
            isFavorite = !existingNote.isFavorite
        )
        upsertNote(updatedNote)
        return updatedNote
    }


    override suspend fun findNoteById(id: String): Note {
        return noteDao.getNoteById(id)?.toNote()
            ?: throw NoSuchElementException("Note with ID $id was not found")
    }
}