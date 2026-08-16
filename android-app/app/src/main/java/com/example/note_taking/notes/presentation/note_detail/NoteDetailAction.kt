package com.example.note_taking.notes.presentation.note_detail

sealed interface NoteDetailAction {
    data class OnTitleChange(val title: String) : NoteDetailAction
    data class OnContentChange(val content: String) : NoteDetailAction
}