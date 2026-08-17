package com.example.note_taking.notes.domain

interface NoteRepository {
    suspend fun getNotes():List<Note>
    suspend fun createNote(title:String,content:String): Note
    suspend fun findNoteById(id:String): Note
}