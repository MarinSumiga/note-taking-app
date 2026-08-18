package com.example.note_taking.notes.presentation.note_list

import com.example.note_taking.notes.domain.Note

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)

sealed interface NoteListAction {
    data class OnSearchQueryChange(val query:String) : NoteListAction
    data class OnNoteFavoriteClick(val id:String): NoteListAction
    data object OnRefresh: NoteListAction
}



