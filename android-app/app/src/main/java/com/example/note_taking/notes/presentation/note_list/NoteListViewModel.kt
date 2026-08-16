package com.example.note_taking.notes.presentation.note_list

import androidx.lifecycle.ViewModel
import com.example.note_taking.notes.domain.notes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NoteListViewModel: ViewModel() {

    private val _state = MutableStateFlow(NoteListState())
    val state = _state.asStateFlow()

    fun onAction(action : NoteListAction){
        when(action){
            is NoteListAction.OnNoteClick ->{

            }
            is NoteListAction.OnSearchQueryChange ->{
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }
            is NoteListAction.OnNoteDelete -> {

            }
            is NoteListAction.OnNoteFavoriteClick -> {
                println("Favorite clicked for note with id ${action.id}")
            }
        }
    }


}