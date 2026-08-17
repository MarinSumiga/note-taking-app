package com.example.note_taking.notes.presentation.note_list

sealed interface NoteListAction {
    data class OnSearchQueryChange(val query:String) : NoteListAction
    data class OnNoteFavoriteClick(val id:String): NoteListAction
    data object OnRefresh: NoteListAction
}