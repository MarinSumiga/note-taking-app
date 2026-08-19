package com.example.note_taking.notes.domain

import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun upsertNote(note: Note)
    fun getNotes(): Flow<List<Note>>
    suspend fun findNoteById(id: String): Note?
    suspend fun toggleFavorite(id: String)
    suspend fun deleteNote(id: String)
}