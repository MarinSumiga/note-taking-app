package com.example.note_taking.notes.domain

interface NoteRepository {
    suspend fun upsertNote(note: Note)
    suspend fun getNotes():List<Note>
    suspend fun findNoteById(id:String): Note
    suspend fun toggleFavorite(id: String): Note
}