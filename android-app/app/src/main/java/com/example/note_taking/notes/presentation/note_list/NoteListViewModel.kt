package com.example.note_taking.notes.presentation.note_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note_taking.notes.domain.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteListViewModel(
    private val repository: NoteRepository
): ViewModel() {

    private val _state = MutableStateFlow(NoteListState())
    val state = _state.asStateFlow()

    init{
        loadNotes()
    }

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

    private fun loadNotes(){
        viewModelScope.launch {
            _state.update{
                it.copy(isLoading = true)
            }
            try{
                val notes = repository.getNotes()
                _state.update {
                    it.copy(
                        notes = notes,
                        searchResults = notes,
                        isLoading = false
                    )
                }
            } catch (error: Exception){
                _state.update{
                    it.copy(
                        errorMessage = error.message,
                        isLoading = false
                    )
                }
            }
        }
    }
}