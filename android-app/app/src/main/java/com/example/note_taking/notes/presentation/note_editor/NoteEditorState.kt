package com.example.note_taking.notes.presentation.note_editor
data class NoteEditorState(
    val noteId: String? = null,
    val noteTitle:String = "",
    val noteContent: String = "",
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)