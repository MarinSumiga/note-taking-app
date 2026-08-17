package com.example.note_taking.notes.domain

interface NoteRepository {
    suspend fun getNotes():List<Note>
}