package com.example.note_taking.notes.presentation.note_editor

sealed interface NoteEditorAction {
    data class OnTitleChange(val title: String) : NoteEditorAction
    data class OnContentChange(val content: String) : NoteEditorAction
    data object OnSaveClick : NoteEditorAction
    data object OnRefresh : NoteEditorAction
}