package com.example.note_taking.notes.presentation.note_list

sealed interface NoteListAction {
    data class OnNoteClick(val id: String) : NoteListAction
    data class OnSearchQueryChange(val query:String) : NoteListAction
    data class OnNoteDelete(val id:String) : NoteListAction
    data class OnNoteFavoriteClick(val id:String): NoteListAction
    data object OnCreateNewNoteClick: NoteListAction
    data object OnRefresh: NoteListAction
}