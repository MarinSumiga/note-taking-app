package com.example.note_taking.notes.presentation.note_detail

import com.example.note_taking.notes.domain.Note

data class NoteDetailState(
    val noteId: String = "",
    val noteTitle:String = "",
    val noteContent: String = "",
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)