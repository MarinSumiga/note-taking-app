package com.example.note_taking.notes.domain

interface NoteRepository {
    suspend fun getNotes():List<Note>
    suspend fun createNote(title:String,content:String): Note
    suspend fun findNoteById(id:String): Note
    suspend fun updateNote(id:String,title:String,content:String): Note
}